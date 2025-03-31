import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<double[]> inputs = new ArrayList<>();
        List<Integer> outputs = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("iris.txt"));
        String line;
        while ((line = br.readLine()) != null){
            String[] flowers = line.split(",");
            double[] values = new double[4];

            for(int i = 0; i < 4; i++){
                values[i] = Double.parseDouble(flowers[i]);
            }

            if (flowers[4].equals("Iris-setosa")) {
                outputs.add(1);
            } else {
                outputs.add(0);
            }

            inputs.add(values); //lista tablic
        }
        br.close();

        Perceptron perceptron = new Perceptron(4,0.5,0.1);

        for(int i = 0; i<100; i++){
            for(int j = 0; j<inputs.size(); j++){
                perceptron.learn(inputs.get(j), outputs.get(j));
            }
        }

        int correct = 0;

        for(int i = 0; i < inputs.size(); i++){
            if(perceptron.compute(inputs.get(i)) == outputs.get(i)) correct++;
        }

        double accuracy = (double) correct/inputs.size() * 100;

        System.out.println("Dokładność: " + accuracy + "%");
    }
}