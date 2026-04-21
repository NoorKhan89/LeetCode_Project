class Task
{
 int freq;
 int time;   
 public Task(int f, int t)
 {
    this.freq = f;
    this.time = t;
 }
}
class Solution {
    public int leastInterval(char[] tasks, int n) {
        // task -> freq ... time ...
        // 1
        // max Heap --> avaiable task
        // queue of task
        // maxHeap ... queue...

        int time =0;
        PriorityQueue<Task> maxHeap = new PriorityQueue<>((a,b)-> b.freq - a.freq);
        Queue<Task> queue = new LinkedList<>();
        // A, A, B, A
        // 0 : A; 3
        // 1: 8: 1
        int[] counts = new int[26];
        for(char t : tasks)
        {
            counts[t - 'A'] = counts[t - 'A'] + 1;
        }
        for(int c : counts)
        {
            if(c > 0)
            {
                 maxHeap.add(new Task(c,1));
            }
        }

        while(!maxHeap.isEmpty() || !queue.isEmpty())
        {
            time = time + 1;

            while(!queue.isEmpty() && queue.peek().time == time)
            {
                maxHeap.add(queue.poll());
            }

            if(!maxHeap.isEmpty())
            {
               Task taskToExicute = maxHeap.poll();
                taskToExicute.freq = taskToExicute.freq - 1;

                taskToExicute.time = time + (n + 1);

                if(taskToExicute.freq > 0)
                {
                    queue.add(new Task(taskToExicute.freq , taskToExicute.time));
                }
            }
        }
        return time;       
    }
}























