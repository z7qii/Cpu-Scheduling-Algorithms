/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpuscheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

import javafx.scene.layout.Priority;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CPUscheduling cpuScheduling = new CPUscheduling();
        PriorityQueue<Process> processes = new PriorityQueue<>();
        String processName ;
        int arrivalTime = 0;
        int burstTime = 0;
        int priority = 0 ;
        int numberOfProcesses = 0;
        int timeQuantum = 0;
        
        while(true){
            System.out.print("Enter number of processes (-1 to exit) : ");
            numberOfProcesses = sc.nextInt();
            if(numberOfProcesses == -1)
                break;
            for(int i = 0 ; i < numberOfProcesses ; i ++){
                System.out.print("Enter process " + (i) + " burst time :");
                burstTime = sc.nextInt();  
                System.out.print("Enter process " + (i) + " arrival time :");
                arrivalTime = sc.nextInt();
                System.out.print("Enter process " + (i) + " priority :");
                priority = sc.nextInt();
                
                
                processName = "P" + (i);
                
                processes.add(new Process(processName, arrivalTime, burstTime, priority));
                       
            }
            
            System.out.print("Enter time Quantum for round robing algorithm :");
            timeQuantum = sc.nextInt();
            
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(":: non preemptive priority ::");
            cpuScheduling.nonPreemptivePriority(processes);
            
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(":: non preemptive SJF ::");
            cpuScheduling.nonPreemptiveSjf(processes);
            
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(":: Round Robin with Quantum " +  timeQuantum + " ::");
            cpuScheduling.roundRobin(processes , timeQuantum);
            
        }
        
        
        
       

       
      

        
    }

    
}