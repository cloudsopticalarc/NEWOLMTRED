package com.spring.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class JwtWithSpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtWithSpringSecurityApplication.class, args);

		System.out.println("\n\n");
//
//		System.err.println("  *****    *******  *******       *****   *******    *****    ******   *******" );
//		System.err.println(" *     *   *      *    *         *           *      *     *   *     *     *   " );
//		System.err.println("*       *  *      *    *         *           *     *       *  *     *     *   " );
//		System.err.println("*       *  *******     *          *****      *     *       *  ******      *   " );
//		System.err.println("*********  *           *               *     *     *********  *   *       *   " );
//		System.err.println("*       *  *           *               *     *     *       *  *    *      *   " );
//		System.err.println("*       *  *        *******       *****      *     *       *  *     *     *   " );
//		System.err.println("                                                                                  ''-_-'    ");
		String[] skull = {
				"0000000000111111110000000000",
				"0000001111111111111111000000",
				"0001111000000000000011110000",
				"0011000000000000000000110000",
				"0110000000000000000000011000",
				"0110000000000000000000011000",
				"1100000111111111111110000110",
				"1100011000000000000011000110",
				"1100110000000000000001100110",
				"1100110000000000000001100110",
				"1100110000000000000001100110",
				"1100110000000000000001100110",
				"1100110000000000000001100110",
				"0110110000000000000001101100",
				"0110011000000000000011001100",
				"0011001111111111111111001100",
				"0011000000000000000000001100",
				"0001110000000000000000011100",
				"0000111100000000000001111000",
				"0000011111111111111111110000",
				"0000000111111111111111000000"
		};

		for (String line : skull) {
			System.out.println(line.replace("1", "*").replace("0", " "));
		}

		System.out.println("\n\n");
		System.err.println("PORT : localhost8080");
		System.err.println("documentation : "+"http://localhost:8080/swagger-ui/index.html#/");
		System.out.println("\n\n");
	}

}
