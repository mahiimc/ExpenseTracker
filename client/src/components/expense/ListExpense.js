import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { findExpensesByUsername, saveExpense } from "../../api/Api";
import { Button, Skeleton, Table, message } from "antd";
import "../../css/expense.css";
import {
  PieChartOutlined,
  BarChartOutlined,
  LineChartOutlined,
} from "@ant-design/icons";
import moment from "moment/moment";
import BarChart from "../charts/BarChart";
import LineChart from "../charts/LineChart";
import PieChart from "../charts/PieChart";
import AddExpense from "./AddExpense";
import {
  addExpenseList,
  setCategoryWiseData,
} from "../../redux/slices/AuthSlice";
import Doughnut from "../charts/Doughnut";

const ListExpense = () => {
  const expenses = useSelector((state) => state.auth.expenses);

  const loggedInUser = useSelector((state) => state.auth.loggedInUser);

  const [loadedComponent, setLoadedComponent] = useState("bar");

  const [page, setPage] = useState(1);
  const dispatch = useDispatch();

  const categories = useSelector(state => state.auth.categories);

  let componentToLoad = <BarChart />;

  switch (loadedComponent) {
    case "bar":
      componentToLoad = <BarChart />;
      break;
    case "pie":
      componentToLoad = <PieChart />;
      break;
    case "line":
      componentToLoad = <LineChart />;
      break;
    case "doughnut":
      componentToLoad = <Doughnut/>
      break;
    default:
      componentToLoad = <BarChart />;
  }

  const columns = [
    {
      title: "S.No",
      dataIndex: "sno",
      key: "sno",
      // render={(value,item,index) => (page-1)*10 + index}
      render: (value, record, index) => (page - 1) * 10 + (index + 1),
    },
    {
      title: "Category",
      dataIndex: "category",
      key: "category",
      filters: categories.map(category => ({
        text: category.name,
        value: category.name,
      })),
      onFilter: (value, record) => record.category.indexOf(value) === 0,
    },
    {
      title: "Description",
      dataIndex: "description",
      key: "description",
    },
    {
      title: "Date",
      dataIndex: "date",
      key: "date",
      render: (epochDate) => moment(epochDate).format("DD-MM-YYYY"),
      sorter: (a, b) => a.date - b.date,
    },
    {
      title: "Amount",
      dataIndex: "amount",
      key: "amount",
      sorter: (a, b) => a.amount - b.amount,
    },
  ];

  useEffect(() => {
    if (expenses.length === 0) {
      findExpensesByUsername(loggedInUser)
        .then((response) => {
          dispatch(addExpenseList(response?.data));
        })
        .catch((error) => {
          message(error?.response?.message);
        });
    }
  }, [loggedInUser]);

  const segregateData = () => {
    let map = new Map();
    if (expenses && expenses.length > 0) {
      expenses.forEach((expense) => {
        if (map.has(expense.category)) {
          const prevAmount = map.get(expense.category);
          let amount = prevAmount + expense.amount;
          map.set(expense.category, amount);
        } else {
          map.set(expense.category, expense.amount);
        }
      });
    }
    const mapArray = Array.from(map);
    const mapObject = Object.fromEntries(mapArray);
    dispatch(setCategoryWiseData(formatData(mapObject)));
  };

  function formatData(mapObject) {
    const data = [];
    Object.entries(mapObject).map(([category, amount], index) => {
      data.push({
        id: index + 1,
        category: category,
        amount: amount,
      });
    });
    return data;
  }

  segregateData();

  return (
    <>
      <div className="expense">
        <AddExpense />
        {expenses.length === 0 ? <Skeleton active/> : <div className="expense-list">
          <Table
            dataSource={expenses}
            columns={columns}
            scroll={{ y: 200 }}
            pagination={{
              onChange(current) {
                setPage(current);
              },
            }}
            rowKey={ record  => record.expenseId}
          />
        </div>
      }
      </div>
     
      <div className="charts">
        <div className="charts-buttons">
          <div className="side-nav-links">
            <ul className="exp-head-link-list">
              <li
                className="chart-link-list-item"
                onClick={() => setLoadedComponent("pie")}
                key="pie"
              >
                <span className="exp-head-link-list-item-icon">
                  <PieChartOutlined />
                </span>
                Pie Chart
              </li>

              <li
                className="chart-link-list-item"
                onClick={() => setLoadedComponent("bar")}
                key={"bar"}
              >
                <span className="exp-head-link-list-item-icon">
                  <BarChartOutlined />
                </span>
                Bar Chart
              </li>

              <li
                className="chart-link-list-item"
                onClick={() => setLoadedComponent("line")}
                key={"line"}
              >
                <span className="exp-head-link-list-item-icon">
                  <LineChartOutlined />
                </span>
                Line Chart
              </li>

              <li
                className="chart-link-list-item"
                onClick={() => setLoadedComponent("doughnut")}
                key={"doughnut"}
              >
                <span className="exp-head-link-list-item-icon">
                  <LineChartOutlined />
                </span>
                Doughnut Chart
              </li>
            </ul>
          </div>
        </div>
        <div className="charts-area">{ expenses.length === 0?<Skeleton active/> : componentToLoad}</div>
      </div>
    </>
  );
};

export default ListExpense;
