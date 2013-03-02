#include <cstdlib>
#include <iostream>
#include <fstream>
#include <unistd.h>
#include <windows.h>
#include <tchar.h>

using namespace std;

void abrir_proceso_java();

int main(int argc, char *argv[])
{
    
    //Medir tiempo inicial
    time_t t1 = time(NULL);

    
    //Si no existe el fichero "partida" arrancamos el programa
    FILE *archivo = fopen("partida", "r");
    
    
    if(!archivo){
                 
    //Crear el archivo
    ofstream fpartida("partida");
    fpartida.close();
    
    //Arrancar programa java
    
    //Abrir nuevo proceso
    
    
    
             //execlp("java", "java", "-jar", "BT.jar &", NULL);
             abrir_proceso_java();
             cout << "Programa iniciado." << endl;
             
    }
    
    //Crear el archivo "jugada" con los datos
    ofstream fs("jugada");
    fs << t1 << "," << argv[1] << "," << argv[2];
    fs.close();
    
    time_t t2 = time(NULL);
    time_t t_final = (t1*1000) + 10000;
    time_t t_espera = t_final - ((t2*1000) + 1000);
    
    cout << "Generado el fichero: " << t2 << " esperando " << t_espera << "ms" << endl;
    
    //Esperar los segundos máximos seguros y cerrar
    Sleep(t_espera);
    
    
    cout<< "Cerrando..." << endl;
    return EXIT_SUCCESS;
}

void abrir_proceso_java(){
       STARTUPINFO si;
    PROCESS_INFORMATION pi;

    ZeroMemory( &si, sizeof(si) );
    si.cb = sizeof(si);
    ZeroMemory( &pi, sizeof(pi) );

//    LPTSTR szCmdline = _tcsdup(TEXT("\"D:\\Windows\\System32\\cmd.exe\""));
    LPTSTR szCmdline = _tcsdup(TEXT("java -jar BT.jar"));

    // Start the child process. 
    if( !CreateProcess( NULL,   // No module name (use command line)
        szCmdline,        // Command line
        NULL,           // Process handle not inheritable
        NULL,           // Thread handle not inheritable
        FALSE,          // Set handle inheritance to FALSE
        0,              // No creation flags
        NULL,           // Use parent's environment block
        NULL,           // Use parent's starting directory 
        &si,            // Pointer to STARTUPINFO structure
        &pi )           // Pointer to PROCESS_INFORMATION structure
    ) 
    {
        printf( "CreateProcess failed (%d).\n", GetLastError() );
        return;
    }

    // Wait until child process exits.
//    WaitForSingleObject( pi.hProcess, INFINITE );

    // Close process and thread handles. 
    CloseHandle( pi.hProcess );
    CloseHandle( pi.hThread );       
}
