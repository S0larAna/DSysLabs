import mpi.*;

public class Main {
    public static void main(String[] args) throws MPIException {
        MPI.Init(args);

        int myrank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int[] message = new int[1];
        int TAG = 0;
        Status status;

        message[0] = myrank;

        if (myrank % 2 == 0) {
            if (myrank + 1 != size) {
                MPI.COMM_WORLD.Send(message, 0, 1, MPI.INT, myrank + 1, TAG);
            }
        } else {
            if (myrank != 0) {
                MPI.COMM_WORLD.Recv(message, 0, 1, MPI.INT, myrank - 1, TAG);
                System.out.println("received: " + message[0]);
            }
        }

        MPI.Finalize();
    }
}