package org.example.exceptions;//package org.example.exceptions;
//
//import io.micronaut.http.HttpRequest;
//import io.micronaut.http.HttpResponse;
//import io.micronaut.http.HttpStatus;
//import io.micronaut.http.server.exceptions.ExceptionHandler;
//import jakarta.inject.Singleton;
//import jakarta.persistence.EntityNotFoundException;
//
//@Singleton
//public class ApplicationExceptionHandler implements ExceptionHandler<Throwable, HttpResponse<DefaultError>> {
//
//	@Override
//	public HttpResponse<DefaultError> handle(HttpRequest request, Throwable e) {
//		if (e instanceof EntityNotFoundException) {
//			return handleException((EntityNotFoundException) e);
//		} else if (e instanceof NullPointerException) {
//			return nullPointer((NullPointerException) e);
//		} else if (e instanceof DispositivosInformaticaTemLancesException) {
//			return diPossuiLances((DispositivosInformaticaTemLancesException) e);
//		} else if (e instanceof LeilaoSemEntidadesFinanceirasAssociadas) {
//			return leilaoSemEntidadeFinanceira((LeilaoSemEntidadesFinanceirasAssociadas) e);
//		} else if (e instanceof ClienteJaCadastradoException) {
//			return clienteJaCadastrado((ClienteJaCadastradoException) e);
//		}
//
//		return HttpResponse.serverError(new DefaultError(HttpStatus.INTERNAL_SERVER_ERROR.getCode(), "Erro desconhecido"));
//	}
//
//	public HttpResponse<DefaultError> handleException(EntityNotFoundException e) {
//		DefaultError erro = new DefaultError(HttpStatus.NOT_FOUND.getCode(), e.getMessage());
//		return HttpResponse.status(HttpStatus.NOT_FOUND).body(erro);
//	}
//
//
//	public HttpResponse<DefaultError> nullPointer(NullPointerException e) {
//		DefaultError erro = new DefaultError(HttpStatus.NOT_FOUND.getCode(), e.getMessage());
//		return HttpResponse.status(HttpStatus.NOT_FOUND).body(erro);
//	}
//
//	public HttpResponse<DefaultError> diPossuiLances(DispositivosInformaticaTemLancesException e) {
//		DefaultError erro = new DefaultError(HttpStatus.UNPROCESSABLE_ENTITY.getCode(), e.getMessage());
//		return HttpResponse.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
//	}
//
//	public HttpResponse<DefaultError> leilaoSemEntidadeFinanceira(LeilaoSemEntidadesFinanceirasAssociadas e) {
//		DefaultError erro = new DefaultError(HttpStatus.UNPROCESSABLE_ENTITY.getCode(), e.getMessage());
//		return HttpResponse.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
//	}
//
//	public HttpResponse<DefaultError> clienteJaCadastrado(ClienteJaCadastradoException e) {
//		DefaultError erro = new DefaultError(HttpStatus.UNPROCESSABLE_ENTITY.getCode(), e.getMessage());
//		return HttpResponse.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
//	}
//
//}
