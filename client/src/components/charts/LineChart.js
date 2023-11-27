import React, { useEffect, useState } from 'react'
import { Line } from 'react-chartjs-2';
import { findCategoryWiseExpenses } from '../../api/Api';
import { Chart as ChartJS, scales } from "chart.js/auto";
import { useSelector } from 'react-redux';
import ChartDataLabels from 'chartjs-plugin-datalabels';


const LineChart = () => {
    ChartJS.register(ChartDataLabels);
    const [data, setData] = useState(null);
    const chartData = useSelector(state => state.auth.categoryWiseData);

    function setChartDataTwo(chartData) {
        if(chartData.length !== 0) {
            setData({
                labels: chartData.map((chart) => chart?.category),
                datasets: [
                {
                    label: "Category",
                    data: chartData.map((chart) => chart?.amount),
                    backgroundColor: [
                    "#2a71d0"
                    ],
                    datalabels: {
                        anchor: 'end',
                        backgroundColor: 'black',
                        borderWidth: 0,
                        color: 'white'
                      },
                    borderColor: "black",
                    borderWidth: 2,
                },
                ],
            })
        }
    }


    useEffect(() => {
        setChartDataTwo(chartData)
    },[chartData]);
    
   
  return data == null ? (<>Loading..</>) : (
    <Line data={data}/>
  )
  
}

export default LineChart
