package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Digite o caminho do arquivo: "); // c:\temp\in5.txt
		String path = sc.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<Employee> list = new ArrayList<>();
			
			String line = br.readLine();
			while (line != null) {
				
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
				
			}
			
			// Email de pessoas com o salário maior que o fornecido
			
			System.out.print("Digite o salário: ");
			double salario = sc.nextDouble();
			
			List<String> emails = list.stream()
					.filter(x -> x.getSalary() > salario)
					.map(x -> x.getEmail())
					.sorted()
					.collect(Collectors.toList());
			
			System.out.println("Email de pessoas com o salário maior que " + String.format("%.2f", salario) + ":");
			emails.forEach(System.out::println);
			
			// Soma do salário de pessoa com o nome começado com 'M'
			
			double sum = list.stream()
					.filter(x -> x.getName().charAt(0) == 'M')
					.map(x -> x.getSalary())
					.reduce(0.0, (x, y) -> x + y);
			
			System.out.println("Soma do salário de pessoa com o nome começado com 'M': " + String.format("%.2f", sum));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
