import React, { useEffect, useState } from 'react'
import { Pie } from 'react-chartjs-2';
import { useSelector } from 'react-redux';
import { Chart as ChartJS, scales } from "chart.js/auto";
import ChartDataLabels from 'chartjs-plugin-datalabels';



const PieChart = () => {
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
                    "#2a71d0",
                    "green",
                    "yellow"
                    ],
                    datalabels: {
                        anchor: 'center',
                        backgroundColor: '#FFF',
                        borderWidth: 0,
                        color: 'black'
                      },
                    borderColor: "black",
                    borderWidth: 2,  
                },
                ],
            })
        }
    }


    useEffect(() => {
        setChartDataTwo(chartData);
    },[chartData]);
    
   
  return data == null ? (<>Loading..</>) : (
    <Pie data={data}  plugins={ChartDataLabels} />
  )
  
}

export default PieChart
