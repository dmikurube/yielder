import static org.mockito.Mockito.*;


public class Main {
    public static void main(String[] args) {
        // Yielder yielder = new RealYielder();
        RealYielder yielder = spy(new RealYielder());
        Thread t1 = new Thread(new Runner(yielder));
        Thread t2 = new Thread(new Runner(yielder));
        when(yielder.behave(any(), anyInt())).thenReturn(0);
        when(yielder.behave(t1, 3)).thenReturn(1);
        when(yielder.behave(t2, 6)).thenReturn(-1);
        t1.start();
        t2.start();
    }
}


class Runner implements Runnable {
    public Runner(Yielder yielder) {
        this.y = yielder;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            y.mayYield(i);
            System.out.println(Thread.currentThread().hashCode() + ": " + i);
        }
    }

    private Yielder y;
}
