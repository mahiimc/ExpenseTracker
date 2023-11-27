import React, { useEffect, useState } from 'react'
import { Bar } from 'react-chartjs-2'
import { Chart as ChartJS, scales } from "chart.js/auto";
import ChartDataLabels from 'chartjs-plugin-datalabels';
import { useSelector } from 'react-redux';

const BarChart = () => {
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
                        backgroundColor: '#FFF',
                        borderWidth: 0,
                        color: 'black'
                      },
                },
                ],
               
            })
        }
    }


    useEffect(() => {
        setChartDataTwo(chartData);
    },[chartData]);
    
   
  return data == null ? (<>Loading..</>) : (
    <Bar data={data} plugins={ChartDataLabels} options = {
        {
            plugins: {
                datalabels: {
                    color: 'red'
                  }
            }
        } 
    }/>
  )
}


export default BarChart
