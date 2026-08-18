import java.util.ArrayList;

// Interface que define o contrato padrão para qualquer tipo de disciplina
interface Disciplina {
    String getName();
    boolean isAprovado();
}

class Graduacao implements Disciplina {
    String nomeDisciplina;
    ArrayList<Double> notas = new ArrayList<>();

    public Graduacao(String nome) {
        this.nomeDisciplina = nome;
    }

    public void addNota(double nota) {
        this.notas.add(nota);
    }

    @Override
    public String getName() {
        return this.nomeDisciplina;
    }

    @Override
    public boolean isAprovado() {
        double soma = 0;
        
        for (int i = 0; i < notas.size(); i++) {
            soma = soma + notas.get(i);
        }
        
        double media = soma / notas.size();
        
        // Na graduação, o aluno é aprovado apenas se a média for maior ou igual a 7.0
        if (media >= 7.0) {
            return true;
        } else {
            return false;
        }
    }
}

class Especializacao implements Disciplina {
    String nomeDisciplina;
    ArrayList<String> conceitos = new ArrayList<>(); 

    public Especializacao(String nome) {
        this.nomeDisciplina = nome;
    }

    public void addConceito(String conceito) {
        this.conceitos.add(conceito);
    }

    @Override
    public String getName() {
        return this.nomeDisciplina;
    }

    @Override
    public boolean isAprovado() {
        // Na especialização, a presença de qualquer conceito "D" resulta em reprovação automática
        for (int i = 0; i < conceitos.size(); i++) {
            if (conceitos.get(i).equals("D")) { 
                return false;
            }
        }
        return true;
    }
}

class Aluno {
    String nome;
    ArrayList<Disciplina> disciplinas = new ArrayList<>();

    public Aluno(String nome) {
        this.nome = nome;
    }

    public void matricular(Disciplina d) {
        this.disciplinas.add(d);
    }

    // Processa a lista de disciplinas e imprime o resultado individual de cada uma
    public void imprimirBoletim() {
        System.out.println("--- Boletim do Aluno: " + this.nome + " ---");
        
        for (int i = 0; i < disciplinas.size(); i++) {
            Disciplina d = disciplinas.get(i);
            
            if (d.isAprovado()) { 
                System.out.println("Disciplina: " + d.getName() + " | Status: Aprovado");
            } else {
                System.out.println("Disciplina: " + d.getName() + " | Status: Reprovado");
            }
        }
    }
}

public class Principal {
    public static void main(String[] args) {
        Aluno a1 = new Aluno("João");

        Graduacao d1 = new Graduacao("Cálculo I");
        d1.addNota(8.0);
        d1.addNota(5.0); 

        Especializacao d2 = new Especializacao("Redes de Computadores");
        d2.addConceito("A");
        d2.addConceito("D"); 
        
        Especializacao d3 = new Especializacao("Engenharia de Software");
        d3.addConceito("A");
        d3.addConceito("B"); 

        a1.matricular(d1);
        a1.matricular(d2);
        a1.matricular(d3);

        a1.imprimirBoletim();
    }
}
