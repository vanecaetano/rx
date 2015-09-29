package br.vanessa.pizzaria.rx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;

public class Pizzaria {
	public static void main(String[] args) {
		pizzas.add("basca");
		pizzas.add("calabreza");
		pizzas.add("mussarela");
		
		Observable<String> garcon = Observable.create(new OnSubscribe<String>() {

			public void call(Subscriber<? super String> o) {
				for(int i=0; i<5; i++){
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					
				}
				o.onNext(getPizza());
				}
				o.onCompleted();
				
				
			}
		})
		.repeat(2)
		.skip(2);
		
		Subscription cliente = garcon.subscribe(new Observer<String>() {

			public void onCompleted() {
				System.out.println("done");
			}

			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}

			public void onNext(String pizza) {
				System.out.println("aceita " + pizza);
			}
		});
		
		Subscription cliente2 = garcon.subscribe(new Observer<String>() {

			public void onCompleted() {
				System.out.println("done");
			}

			public void onError(Throwable arg0) {
				System.out.println("Erro ao entregar pizza");
			}

			public void onNext(String pizza) {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("2, aceita " + pizza);
			}
		});
		
	
	}
	
	static List<String> pizzas = new ArrayList<String>();
	
	public static String getPizza() {
		return pizzas.get(ThreadLocalRandom.current().nextInt(pizzas.size()));
	}
	
}
