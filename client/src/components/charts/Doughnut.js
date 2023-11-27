import React, { useEffect, useState } from 'react'
import { Chart as ChartJS  } from "chart.js/auto";
import { useSelector } from 'react-redux';
import ChartDataLabels from 'chartjs-plugin-datalabels';
import { Doughnut as DoughnutChart } from 'react-chartjs-2';

const Doughnut = () => {
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
                        'rgb(255, 99, 132)',
                        'rgb(54, 162, 235)',
                        'rgb(255, 205, 86)',
                        'rgb(255, 25, 86)'
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
    <DoughnutChart data={data}/>
  )
  
}

export default Doughnut
