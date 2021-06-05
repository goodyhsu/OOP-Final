package model;

public class Counter {
    private int current_time;
    private int time_limit;
    private boolean running;
    public Counter(int time_limit){
        this.current_time = 0;
        this.time_limit = time_limit;
        running = true;
        new Thread(this::setCounter).start();
    }
    private void setCounter() {
        while (running) {
            delay(15);
            current_time += 1;
        }
    }

    private void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean time_up(){
        if (time_limit == -1)
            return false;
        else if (current_time >= time_limit) {
            stopCounter();
            return true;
        }
        return false;
    }
    public void stopCounter() {
        running = false;
    }
}
