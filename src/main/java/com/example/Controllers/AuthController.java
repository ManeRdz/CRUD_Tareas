package com.example.Controllers;

import com.example.Models.HttpResponseHandler;
import com.example.Models.LoginRequestDTO;
import com.example.Models.RegisterRequestDTO;
import com.example.Service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private IUserService iUserService;
    @PostMapping(value = "login")
    public ResponseEntity<HttpResponseHandler> login(@RequestBody LoginRequestDTO request){
        try{
            var res = iUserService.LoginUser(request);
            HttpResponseHandler response = HttpResponseHandler.builder()
                    .Success(true)
                    .Error("")
                    .Message("Autenticado Correctamente.")
                    .Data(res)
                    .build();
            return ResponseEntity.ok(response);
        }catch (Exception ex){
            HttpResponseHandler response = HttpResponseHandler.builder()
                    .Success(false)
                    .Error(ex.getMessage())
                    .Message("Verifica las credenciales")
                    .Data(null)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }

    }
    @PostMapping(value = "register")
    public ResponseEntity<HttpResponseHandler> register(@RequestBody RegisterRequestDTO request ){

        try{
            var userExists = iUserService.FindByUserName(request.getUserName());
            if(userExists.isPresent()){
                HttpResponseHandler response = HttpResponseHandler.builder()
                        .Success(false)
                        .Error("")
                        .Message("Ya existe ese nombre de usuario.")
                        .Data(null)
                        .build();
                return ResponseEntity.ok(response);

            }else{
                var res = iUserService.RegisterUser(request);

                HttpResponseHandler response = HttpResponseHandler.builder()
                        .Success(true)
                        .Error("")
                        .Message("Registrado Correctamente")
                        .Data(res)
                        .build();
                return ResponseEntity.ok(response);
            }

        }catch (Exception ex){
            HttpResponseHandler response = HttpResponseHandler.builder()
                    .Success(false)
                    .Error(ex.getMessage())
                    .Message("Ha ocurrido un error al realizar la operación.")
                    .Data(null)
                    .build();
            return ResponseEntity.ok(response);
        }

    }
}
