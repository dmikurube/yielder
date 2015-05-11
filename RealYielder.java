import java.util.concurrent.locks.ReentrantLock;


public class RealYielder extends Yielder {
    public RealYielder() {
        this.lock = new ReentrantLock();
    }

    @Override
    public void mayYield(int n1) {
        Thread thread = Thread.currentThread();
        int behavior = behave(thread, n1);
        if (behavior < 0) {
            lock.unlock();
        } else if (behavior > 0) {
            lock.lock();
        }
    }

    public int behave(Thread thread, int n1) {
        return 0;
    }

    private ReentrantLock lock;
}
