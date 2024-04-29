/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpuscheduling;

import javax.lang.model.util.ElementScanner14;

public class Process implements Comparable<Process>{
    String processName;
    int arrivalTime ;
    int OriginalBurstTime;
    int priority;
    int turnAroundTime;
    int waitingTime;
    int finishTime;
    int remainingBurstTime;
     public Process(String processName , int arrivalTime, int burstTime, int priority , int trunAroundTime , int waitingTime , int finishTime) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.OriginalBurstTime = burstTime;
        this.priority = priority;
        this.turnAroundTime = turnAroundTime;
        this.waitingTime = waitingTime;
        this.finishTime = finishTime;
        this.remainingBurstTime = OriginalBurstTime;
    }
     
    public int getRemainingBurstTime(){
        return remainingBurstTime;
    }
    
    public void setRemainingBurstTime(int remainingBurstTime){
        this.remainingBurstTime = remainingBurstTime;
    }
    public void setFinishTime(int finishTime){
        this.finishTime = finishTime;
    }

    public int getFinishTime(){
        return finishTime;
    }
    public int getTurnAroundTime() {
        return turnAroundTime;
    }
    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }
    public int getWaitingTime() {
        return waitingTime;
    }
    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }
    public String getProcessName() {
        return processName;
    }
    public void setProcessName(String processName) {
        this.processName = processName;
    }
    public Process(){}
    public Process(String processName , int arrivalTime, int burstTime, int priority) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.OriginalBurstTime = burstTime;
        this.priority = priority;
    }
    public int getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public int getOriginalBurstTime() {
        return OriginalBurstTime;
    }
    public void setOriginalBurstTime(int burstTime) {
        this.OriginalBurstTime = burstTime;
    }
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    @Override
    public int compareTo(Process o) {
        if (arrivalTime < o.arrivalTime)
            return -1;
        else if(arrivalTime > o.arrivalTime)
            return 1;
        else if(OriginalBurstTime < o.OriginalBurstTime)
            return -1;
        else if(OriginalBurstTime > o.OriginalBurstTime)
            return 1;
        return 0;
    }

   

  
   
}
