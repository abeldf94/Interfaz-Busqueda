package com.abeldf.busqueda;
import java.awt.Point;
import java.util.Vector;

import com.abeldf.matrix.Matrix;


public class AlgoritmoEstrella {
    
    private Vector<Nodo> listaC = new Vector<Nodo>();
    private Vector<Nodo> listaA = new Vector<Nodo>();
    public Vector<Point> mejorCamino = new Vector<Point>();
    private int [][] matriz;
    
    //Constructor
    public AlgoritmoEstrella(){
    	listaA.clear();
        listaC.clear();
        mejorCamino.clear();
    }
    
    //Algoritmo de busqueda A*: Recibe las coordenadas de la casilla inicio y la final
    public Vector<Point> buscar(int inicioX, int inicioY, int finalX, int finalY, Matrix tabl){
       
        matriz = tabl.getMatrix();
        
        //Construimos el nodo Inicial y Final
        Nodo finals = new Nodo(null, null, finalX, finalY, 0);
        Nodo inicio = new Nodo(null, finals, inicioX, inicioY, 0);
        //Añadimos el nodo inicial a la lista abierta
        addNodoListaA(inicio);
        
        /*System.out.println("Listas con nodo inicial");
        System.out.print("ListaA: ");
        for (Nodo n: ListaA) {
        	System.out.print( "(" + n.getX() + ", " + n.getY() + ") / " + n.getcoste() + ", ");
        }
        System.out.println();
        System.out.print("ListaC: ");
        for (Nodo n: ListaC) {
        	System.out.print( "(" + n.getX() + ", " + n.getY() +") / " + n.getcoste() + ", ");
        }
        System.out.println();*/
        
        //Mientras la lista abierta no se vacie, ir buscando los nodos adyacentes hasta llegar al nodo final
        while(listaA.size()>0){
            Nodo nodoActual = listaA.get(listaA.size()-1);
            listaA.remove(listaA.size()-1);
            //System.out.println("Tengo el nodo: " + NodoActual.getX() + ", " + NodoActual.getY() + " y lo borro de la lista A");
            //Comprobar si es el nodo final
            if(nodoActual.esIgual(finals)){
                while(nodoActual != null){
                    //Construir un vector de Points (x,y) con el camino optimo
                    Point P=new Point(nodoActual.getX(), nodoActual.getY());
                    mejorCamino.add(P);
                    nodoActual = nodoActual.fatherNodo;
                }
            return mejorCamino;
            }
            
            //Si no es el nodo final, buscar nodos adyacentes y añadirlos a la lista abierta
            Vector<Nodo> posiblesNodos = encontrarNodosAdyacentes(nodoActual, finals);
            
            //Para que realice un muestreo de los nodos adyacentes sin ocupar (!=1) para ver si lo hace de manera correcta
            /*System.out.println("Del nodo actual : " + NodoActual.getX() + ", " + NodoActual.getY());
            
            if(PosiblesNodos != null){
            	for(int i=0; i < PosiblesNodos.size(); i++){
            		System.out.println(PosiblesNodos.get(i).getX() + ", " + PosiblesNodos.get(i).getY());
            	}
            }
            
            System.out.print("ListaA: ");
            for (Nodo n: ListaA) {
            	System.out.print( "(" + n.getX() + ", " + n.getY() + ") / " + n.getcoste() + ", ");
            }
            System.out.println();
            System.out.print("ListaC: ");
            for (Nodo n: ListaC) {
            	System.out.print( "(" + n.getX() + ", " + n.getY() +") / " + n.getcoste() + ", ");
            }
            System.out.println();*/
            
            for (Nodo comprobar : posiblesNodos) {
            	//System.out.println("Comprobando el nodo: " + comprobar.getX() + ", " + comprobar.getY());
                if(!containListaC(comprobar)){
                    if(containListaA(comprobar)){
                        if(comprobar.costoG >= nodoActual.costoG){
                            continue;
                        }
                    }
                    addNodoListaA(comprobar);
                }
            }
            listaC.add(nodoActual);
            /*System.out.print("ListaA: ");
            for (Nodo n: ListaA) {
            	System.out.print( "(" + n.getX() + ", " + n.getY() + ") / " + n.getcoste() + ", ");
            }
            System.out.println();
            System.out.print("ListaC: ");
            for (Nodo n: ListaC) {
            	System.out.print( "(" + n.getX() + ", " + n.getY() +") / " + n.getcoste() + ", ");
            }
            System.out.println();
            System.out.println( " ");*/
        }
        return null;       
    }
    
    
    //Funcion que añade y ordena Nodos de la lista Abierta
    public void addNodoListaA(Nodo nodo){
        int i=0;
        int costo = nodo.costoTotal;

        while ((listaA.size()>i) && (costo < listaA.get(i).costoTotal))
            i++;
        listaA.insertElementAt(nodo, i);
    }
    
    
    //funcion que comprueba si ya contiene el nodo la lista cerrada
    public boolean containListaC(Nodo nodo){
        Nodo nodoC;
        for(int i = 0; i < listaC.size(); i++){
            nodoC = listaC.get(i);
            if((nodo.getX() == nodoC.getX()) && (nodo.getY() == nodoC.getY())){
                return true;
            }
        }
        return false;
    }
    
    
    //funcion que comprueba si ya contiene el nodo la lista abierta
    public boolean containListaA(Nodo nodo){
        Nodo nodoC;
        
        for(int i = 0; i < listaA.size(); i++){
            nodoC = listaA.get(i);
            if((nodo.getX() == nodoC.getX()) && (nodo.getY() == nodoC.getY())){
                return true;
            }
        }
        return false;
    }
    
    
    //Funcion que busca nodos adyacentes comprobando si la casillas adyacentes estan ocupados y los devuelve en un vector
    public Vector<Nodo> encontrarNodosAdyacentes(Nodo nodoActual, Nodo nodoFinal){
        
    	Vector<Nodo> nodosAdyacentes = new Vector<Nodo>();
        int y = nodoActual.getY(); //fila del nodo actual
        int x = nodoActual.getX(); //columna del nodo actual
        

        
        if(((x >= 0) && ( x <= matriz.length-1))){
        	if(((y > 0) && (y <= matriz[0].length)) ){
        		if( matriz[x][y-1] != 1){ //Vemos si la casilla de la izquierda al nodo actual está sin ocupar
        			Nodo Alfa = new Nodo(nodoActual, nodoFinal, x, (y-1), 1+nodoActual.costoG);
        			if(nodoActual.fatherNodo != Alfa){
        				nodosAdyacentes.add(Alfa);
        			}
        		}
        	}
            
    	  
        	if(((y >= -1) && (y < matriz[0].length-1)) ){
        		if(matriz[x][y+1] != 1){ //Vemos si la casilla de la derecha al nodo actual está sin ocupar
        			Nodo Alfa = new Nodo(nodoActual, nodoFinal, x, (y+1), 1+nodoActual.costoG);
        			if(nodoActual.fatherNodo != Alfa){
        				nodosAdyacentes.add(Alfa);
        			}
        		}	
        	}
        }
        
        
        if(((y >= 0) && ( y <= matriz[0].length-1))){
        	if(((x >= -1) && (x < matriz.length-1)) ){
        		if(matriz[x+1][y] != 1){ //Vemos si la casilla de abajo al nodo actual está sin ocupar
        			Nodo Alfa = new Nodo(nodoActual, nodoFinal, (x+1), y, 1+nodoActual.costoG);
		            if(nodoActual.fatherNodo != Alfa){
		            	nodosAdyacentes.add(Alfa);
		            }
		        }
      	  	}
		        
        	if(((x > 0) && (x <= matriz.length)) ){
        		if(matriz[x-1][y] != 1){ //Vemos si la casilla de arriba al nodo actual está sin ocupar
        			Nodo Alfa = new Nodo(nodoActual, nodoFinal, (x-1), y, 1+nodoActual.costoG);
		            if(nodoActual.fatherNodo != Alfa){
		                nodosAdyacentes.add(Alfa);
		            }
		        }
      	  	}
        }
        return nodosAdyacentes;
    }
}