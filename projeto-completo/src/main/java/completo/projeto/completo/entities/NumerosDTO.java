package completo.projeto.completo.entities;



public class NumerosDTO {
    
    private int id;

    private int numeroA;

    private int numeroB;

    public NumerosDTO(Numeros entity) {
        this.id = entity.getId();
        this.numeroA = entity.getNumeroA();
        this.numeroB = entity.getNumeroB();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroA() {
        return numeroA;
    }

    public void setNumeroA(int numeroA) {
        this.numeroA = numeroA;
    }

    public int getNumeroB() {
        return numeroB;
    }

    public void setNumeroB(int numeroB) {
        this.numeroB = numeroB;
    }

}
