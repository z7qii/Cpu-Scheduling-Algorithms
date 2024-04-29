/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpuscheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;

public class CPUscheduling {
    public void roundRobin(PriorityQueue<Process> processes, int quantum){
        StringBuilder output = new StringBuilder();
        
        PriorityQueue<Process> processesCopy = new PriorityQueue<>(makeCopy(processes));
        LinkedList<Process> list = new LinkedList<>(processesCopy);
        int totalTime = executeRoundRobin(processesCopy, quantum , output);
        
        System.out.println(printOutput(output , totalTime));
        printClculations(list);
         
    }
    
    private int executeRoundRobin(PriorityQueue<Process> processes, int quantum , StringBuilder output) {
         Queue<Process> queue = new LinkedList<>();
         queue.add(processes.poll());
        int time = 0;
        
        while (!queue.isEmpty()) {
            output.append("|" + queue.peek().getProcessName());
            
            for (int i = 0; i < quantum; i++) {

                output.append("   ");
                queue.peek().setRemainingBurstTime(queue.peek().getRemainingBurstTime() - 1);
                time++;
                if (queue.peek().getRemainingBurstTime() == 0) {
                    break;
                }
            }

            while (!processes.isEmpty() && processes.peek().getArrivalTime() <= time)
                queue.add(processes.remove());

            if (queue.peek().getRemainingBurstTime() > 0)
                queue.add(queue.poll());
            else{
                queue.peek().setFinishTime(time);
                queue.poll();
            }

                
        }

        output.append("|");
        return time;
        
    }


    public void nonPreemptiveSjf(Queue<Process> processes){
       
        
        StringBuilder output = new StringBuilder();
        LinkedList<Process> copy1 = new LinkedList<>(makeCopy(processes));
        LinkedList<Process> copy2 = new LinkedList<>(copy1);
        int totalTime = executeNonPreemptiveSjf(copy1 , output);
        
        System.out.println(printOutput(output , totalTime));
        printClculations(copy2);
         
        
    }
    private int executeNonPreemptiveSjf(LinkedList<Process> list , StringBuilder output){
        
        int time = 0;
        Process processToServe = list.peek();
        int arrivalTime = processToServe.getArrivalTime();
        int burstTime = processToServe.getRemainingBurstTime();
        for(int i = 0 ; i < list.size() ; i ++){
            if(list.get(i).getArrivalTime() <= arrivalTime){
                if(list.get(i).getRemainingBurstTime() < processToServe.getRemainingBurstTime()){
                    processToServe = list.get(i);
                }
            }
        }
        list.remove(processToServe);
        output.append("|" + processToServe.getProcessName());
        while (true) {
            
            output.append("   ");
            processToServe.setRemainingBurstTime(processToServe.getRemainingBurstTime() - 1);
            time++;
            if (processToServe.getRemainingBurstTime() == 0) {
                processToServe.setFinishTime(time);
                if(list.size() == 0)
                    break;
                if(list.size() != 0){
                    processToServe = list.peek();
                    for(int i = 0 ; i < list.size() ; i++){
                        if(list.get(i).getArrivalTime() <= time){
                            if(list.get(i).getRemainingBurstTime() < processToServe.getRemainingBurstTime()){
                                processToServe = list.get(i);
                            }
                        }
                    }
                    
                    output.append("|" + processToServe.getProcessName());
                    
                    list.remove(processToServe);
                }
                    
            }
        }

            
           

        output.append("|");
        return time;
    }
    

    private String printOutput(StringBuilder output , int totalTime){
        System.out.println("************************************************** GIANT CHART **************************************************");
        StringBuilder str = new StringBuilder();
        appendDashes(str , totalTime , output);
        str.append(output.toString());
        str.append('\n');
        appendDashes(str , totalTime , output);
        int counter = 0;
        for (int i = 0; i < output.length(); i++) {
            if (output.charAt(i) == '|') {
                if (counter > 9)
                    i += 2;
                else
                    i++;
                str.append(counter);
                
            }
            str.append(" ");
           
            if (i % 3 == 0) {
                counter++;
            }
        }

        return str.toString();

    }

    private void appendDashes(StringBuilder str , int totalTime  , StringBuilder output) {
        
        for (int i = 0; i < totalTime; i++) {
            
            str.append("===");
        }
        int counter = 0;
        for(int i = 0 ; i < output.length() ; i++){
            if(output.charAt(i) == '|')
                counter ++;
        }
        
        counter --;
        for (int i = 0; i < counter; i++) {
           
            str.append("===");
        }
        //str.append("===");
        str.append('\n');

    }

    public void nonPreemptivePriority(Queue<Process> processes){
        StringBuilder output = new StringBuilder();
        //Queue<Process> queue = new LinkedList<>(processes);
        LinkedList<Process> copy1 = new LinkedList<>(makeCopy(processes));
        LinkedList<Process> copy2 = new LinkedList<>(copy1);
        int totalTime = executeNonPreemptivePriority(copy1 , output);
        
        System.out.println(printOutput(output , totalTime));
        printClculations(copy2);
        
        
    }

    private int executeNonPreemptivePriority(LinkedList<Process> list , StringBuilder output){
        int time = 0;
        Process processToServe = list.peek();
        int arrivalTime = processToServe.getArrivalTime();
        
        for(int i = 0 ; i < list.size() ; i ++){
            if(list.get(i).getArrivalTime() <= arrivalTime){
                if(list.get(i).getPriority() < processToServe.getPriority()){
                    processToServe = list.get(i);
                }
            }
        }
        list.remove(processToServe);
        output.append("|" + processToServe.getProcessName());
        while (true) {
            
            output.append("   ");
            processToServe.setRemainingBurstTime(processToServe.getRemainingBurstTime() - 1);
            time++;
            if (processToServe.getRemainingBurstTime()== 0) {
                processToServe.setFinishTime(time);
                if(list.size() == 0)
                    break;
                if(list.size() != 0){
                    processToServe = list.peek();
                    for(int i = 0 ; i < list.size() ; i++){
                        if(list.get(i).getArrivalTime() <= time){
                            if(list.get(i).getPriority() < processToServe.getPriority()){
                                processToServe = list.get(i);
                            }
                        }
                    }
                    list.remove(processToServe);
                    output.append("|" + processToServe.getProcessName());
                }
                    
            }
        }

            
           

        output.append("|");
        return time;
    }

    public void calculateWaitingTime(LinkedList<Process> list){
        for(int i = 0 ; i < list.size() ; i++){
            list.get(i).setWaitingTime(list.get(i).getTurnAroundTime() - list.get(i).getOriginalBurstTime());
        }
    }

    public void calculateTurnAroundTime(LinkedList<Process> list){
        for(int i = 0 ; i < list.size() ; i++){
            list.get(i).setTurnAroundTime(list.get(i).getFinishTime() - list.get(i).getArrivalTime());
        }
    }
    
    
    public double calculateAverageWaitingTime(LinkedList<Process> list){
        double averageTime = 0;
        int numOfProcesses = list.size();
        
        for(int i = 0 ; i < list.size() ; i++){
            averageTime += list.get(i).getWaitingTime();
        }
        averageTime = averageTime / numOfProcesses;
        return averageTime;
    }
    
    public double calculateAverageTurnAroundTime(LinkedList<Process> list){
        double averageTime = 0;
        int numOfProcesses = list.size();
        
        for(int i = 0 ; i < list.size() ; i++){
            averageTime += list.get(i).getTurnAroundTime();
        }
        averageTime = averageTime / numOfProcesses;
        return averageTime;
    }

    public void printClculations(LinkedList<Process> list){
        System.out.println();
        System.out.println();
        //System.out.println("************************************************** CALCULATION TABLE **************************************************");
        

        calculateTurnAroundTime(list);
        calculateWaitingTime(list);
        int numOfSpaces = 16;
        System.out.println("=============================================================================================================");
        System.out.println("||               ||"+"  ARRIVAL TIME  ||" + "    BURST TIME  ||" + "  FINISHING TIME||" + " TURNAROUND TIME||" + "   WAITING TIME ||");
        for(int i = 0 ; i < list.size() ; i++){
            System.out.println("=============================================================================================================");
            
            System.out.println("|| " + list.get(i).getProcessName() + printSpaces(17 - (3 + String.valueOf(list.get(i).getProcessName()).length())) + " " + list.get(i).getArrivalTime() + printSpaces(17 - (2 + String.valueOf(list.get(i).getArrivalTime()).length()))+ " " + list.get(i).getOriginalBurstTime() + printSpaces(17 - (2 + String.valueOf(list.get(i).getOriginalBurstTime()).length())) + " "+ list.get(i).getFinishTime() + printSpaces(17 - (2 + String.valueOf(list.get(i).getFinishTime()).length())) + " " + list.get(i).getTurnAroundTime() + printSpaces(17 - (2 + String.valueOf(list.get(i).getTurnAroundTime()).length())) + " " + list.get(i).getWaitingTime() + printSpaces(17 - (2 + String.valueOf(list.get(i).getWaitingTime()).length())));
            
        }
        
        System.out.println("AVERAGE TURNAROUND TIME = " + calculateAverageTurnAroundTime(list) );
        System.out.println("AVERAGE WAITING TIME = " + calculateAverageWaitingTime(list) );

    }
    
    public String printSpaces(int numOfSpaces){
        StringBuilder str = new StringBuilder();
        for(int i = 0 ; i < numOfSpaces ; i++){
           str.append(" ");
        }
        str.append("||");
        return str.toString();
    }
    
    public LinkedList<Process> makeCopy(Queue<Process> processes){
         LinkedList<Process> list = new LinkedList<>(processes);
        LinkedList<Process> copy  = new LinkedList<>();
        for(int i = 0 ; i < list.size() ; i++){
            Process process = list.get(i);
            copy.add(new Process(process.getProcessName() , process.getArrivalTime() , process.getOriginalBurstTime() , process.getPriority() , process.getTurnAroundTime() , process.getWaitingTime() , process.getFinishTime()));
        }
        
        return copy;
    }
    

//     a) Turnaround time for each process
// b) Total and Average Turnaround time for the entire processes
// c) Waiting time for each process
// d) Total and Average Waiting time for the entire processes

// Turnaround time = (Finish Time – Arrival Time)
// Waiting time=(Turnaround time – CPU burst time)

    
}
