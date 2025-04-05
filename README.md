# Creating and managing FHIR stores ***

## FHIR versions DSTU2, STU3, and R4. 
R4 (Release 4)
Released: 2019

### Purpose: R4 is the first "normative" release of FHIR, meaning it is a more stable version that is considered ready for widespread adoption in healthcare systems.

### Characteristics:

Introduced major improvements and additional resources like FHIR for clinical data exchange and clinical decision support.

FHIR for mobile, web, and cloud-based apps with a focus on interoperability and integration with other healthcare systems.

First version to be "normative": This means that the FHIR specifications in R4 are considered stable and will not change significantly, ensuring long-term compatibility.

Added features for security, auditing, patient privacy, and data integrity.

Backward Compatibility: R4 includes most features from STU3 and DSTU2 but with changes for better consistency and enhanced security.

https://hl7.org/fhir/R4/

### How to create, edit, view, list, and delete Fast Healthcare Interoperability Resources (FHIR) stores. FHIR stores hold FHIR resources, such as Claim resources, Patient resources, Medication resources, and more.

C:\Program Files (x86)\Google\Cloud SDK>``` gcloud auth login ```
Your browser has been opened to visit:

C:\Program Files (x86)\Google\Cloud SDK>``` gcloud projects create healthcare-system-api ```
Create in progress for [https://cloudresourcemanager.googleapis.com/v1/projects/healthcare-system-api].
Waiting for [operations/create_project.global.7912408794300558084] to finish...done.
Enabling service [cloudapis.googleapis.com] on project [healthcare-system-api]...
Operation "operations/acat.p2-1083335448446-b043be43-e456-4dbb-b5ff-a83e6673d513" finished successfully.

C:\Program Files (x86)\Google\Cloud SDK>``` gcloud config set project healthcare-system-api ```
Updated property [core/project].

## To get Access Token from Google Cloud: https://cloud.google.com/
Welcome to Cloud Shell! Type "help" to get started.
Your Cloud Platform project in this session is set to healthcare-system-api.
Use `gcloud config set project [PROJECT_ID]` to change to a different project.

rekaharisri@cloudshell:~ (healthcare-system-api)$ ``` gcloud auth application-default print-access-token ```

C:\Program Files (x86)\Google\Cloud SDK> ``` gcloud services enable healthcare.googleapis.com ```
Operation "operations/acf.p2-1083335448446-9a636579-09c8-45fe-abe6-3ff947fe28c8" finished successfully.

C:\Windows\System32> ``` gcloud auth login ```
You are now logged in as [rekaharisri@gmail.com].                               
Your current project is [healthcare-system-api].  You can change this setting by running:                     
$ gcloud config set project PROJECT_ID 

C:\Windows\System32> ``` gcloud auth list ```                                                        
Credentialed Accounts     
ACTIVE  ACCOUNT rekaharisri@gmail.com     

To set the active account, run:                                            
 $ ``` gcloud config set account `ACCOUNT`  ```

C:\Windows\System32> ``` gcloud auth application-default print-access-token  ```                                                                                                                                    
C:\Windows\System32> ``` gcloud services enable 
healthcare.googleapis.com ```                                                                               C:\Windows\System32> ``` gcloud auth application-default login --scopes=https://www.googleapis.com/auth/cloud-platform      ```                                                                        
Your browser has been opened to visit:                                                                            Credentials saved to file: [C:\Users\nreka\AppData\Roaming\gcloud\application_default_credentials.json]                                                                                                      
These credentials will be used by any library that requests Application Default Credentials Quota project "healthcare-system-api" was added to ADC which can be used by Google client libraries for billing and quota. Note that some services may still bill the project owning the resource.                                                                                                            C:\Windows\System32> ``` gcloud auth application-default print-access-token   ```                                                  
  
``` mvn clean test ```

C:\Windows\System32> ``` gcloud healthcare datasets list --location=us-central1 --project=healthcare-system-api   ```  
    ID                LOCATION           TIMEZONE  ENCRYPTION                   
   healthcare_data    us-central1            Google-managed key     

#### TO DELETE DATASET:
``` gcloud healthcare datasets delete healthcare_data --location=us-central1 --project=healthcare-system-api ```

#### TO DELETE FHIR STORE:
``` gcloud healthcare fhir-stores delete Dar_83 --dataset=healthcare_data --location=us-central1 --project=healthcare-system-api ```

![alt text](fhirImages/image-3.png)

### Create Dataset:
![alt text](fhirImages/image-4.png)

### Patch Timezone in dataset :
![alt text](fhirImages/image-5.png)

### create FHIR store :
![alt text](fhirImages/image-6.png)

![alt text](fhirImages/Screenshot 2025-04-01 101456.png)
![alt text](fhirImages/Screenshot 2025-04-01 101549.png)

### Get FHIR Stores
![alt text](fhirImages/image-7.png)

### To Open Report in github:
Open your GitHub repo.
Navigate to the reports/ folder where your Extent Report (TestReport....html) is stored.
Click on the file and Download it.
Open it in a browser.

## Create Docker image
docker build -t fhir-googlecloudapi-bdd-cucumber-framework . 
![alt text](fhirImages/image-8.png)

## Azure:

![alt text](fhirImages/image-9.png)
##[error]No hosted parallelism has been purchased or granted. To request a free parallelism grant, please fill out the following form https://aka.ms/azpipelines-parallelism-request

** U have to get Subscription and then only u can run pipeline in azure **
![alt text](fhirImages/image-10.png)

It will Show like this , if u run pipeline when u have paid and got the subscription
![alt text](fhirImages/image-11.png)
![alt text](fhirImages/image-14.png)
![alt text](fhirImages/image-12.png)
![alt text](fhirImages/image-13.png)

PS C:\Users\nreka\vscodedevops\FHIR-Google-Cloud-API> ```kubectl config current-context```
minikube
PS C:\Users\nreka\vscodedevops\FHIR-Google-Cloud-API>``` minikube start```
PS C:\Users\nreka\vscodedevops\FHIR-Google-Cloud-API> ```minikube status```
PS C:\Users\nreka\vscodedevops\FHIR-Google-Cloud-API> ```kubectl config use-context docker-desktop```
Switched to context "docker-desktop".
PS C:\Users\nreka\vscodedevops\FHIR-Google-Cloud-API>``` kubectl config current-context```
docker-desktop
PS C:\Users\nreka\vscodedevops\FHIR-Google-Cloud-API> ```kubectl get nodes```
NAME             STATUS   ROLES           AGE   VERSION
docker-desktop   Ready    control-plane   11s   v1.31.4
PS C:\Users\nreka\vscodedevops\FHIR-Google-Cloud-API> ```kubectl apply -f deployment-service.yml```
or kubectl apply -f deployment-service.yml --validate=false
PS C:\Users\nreka\vscodedevops\FHIR-Google-Cloud-API> ```kubectl apply -f deployment-service.yml --validate=false```
deployment.apps/fhir-restassured-deployment created
service/restassured-ssvc created
PS C:\Users\nreka\vscodedevops\FHIR-Google-Cloud-API> kubectl get deployments```
NAME                          READY   UP-TO-DATE   AVAILABLE   AGE
fhir-restassured-deployment   0/2     2            0           46s
PS C:\Users\nreka\vscodedevops\FHIR-Google-Cloud-API> ```kubectl get services```
NAME               TYPE           CLUSTER-IP     EXTERNAL-IP   PORT(S)        AGE
kubernetes         ClusterIP      10.96.0.1      <none>        443/TCP        96s
restassured-ssvc   LoadBalancer   10.109.98.84   localhost     80:30645/TCP   53s
PS C:\Users\nreka\vscodedevops\FHIR-Google-Cloud-API> ```kubectl get pods```
NAME                                           READY   STATUS             RESTARTS   AGE
fhir-restassured-deployment-64d7fd4c44-jwkzz   0/1     ImagePullBackOff   0          58s
fhir-restassured-deployment-64d7fd4c44-kkqcm   0/1     ErrImagePull       0          58s
PS C:\Users\nreka\vscodedevops\FHIR-Google-Cloud-API> ```kubectl get svc restassured-ssvc```
NAME               TYPE           CLUSTER-IP     EXTERNAL-IP   PORT(S)        AGE
restassured-ssvc   LoadBalancer   10.109.98.84   localhost     80:30645/TCP   70s
PS C:\Users\nreka\vscodedevops\FHIR-Google-Cloud-API> ```kubectl describe deployment fhir-restassured-deployment```
Name:                   fhir-restassured-deployment
Annotations:            deployment.kubernetes.io/revision: 1
Selector:               app=fhir-restassured
Replicas:               2 desired | 2 updated | 2 total | 0 available | 2 unavailable
Pod Template:
  Labels:  app=fhir-restassured
  Containers:
   fhir-restassured:
    Image:      containerregistrynv.azurecr.io/containerregistrynv/fhir-restassured:latest
    Port:       8080/TCP
    Host Port:  0/TCP
    
PS C:\Users\nreka\vscodedevops\FHIR-Google-Cloud-API> ```docker images```
REPOSITORY     TAG       IMAGE ID       CREATED          SIZE
fhir-googlecloudapi-bdd-cucumber-framework   latest  5f77df9a546d   40 minutes ago   1.29GB

## AWS
PS C:\Users\nreka\vscodedevops\FHIR-Google-Cloud-API> ```minikube image load fhir-googlecloudapi-bdd-cucumber-framework```
PS C:\Users\nreka\vscodedevops\FHIR-Google-Cloud-API> ```kubectl apply -f deployment-service.yml```
deployment.apps/fhir-restassured-deployment created
service/restassured-ssvc created

PS C:\Users\nreka\vscodedevops\FHIR-Google-Cloud-API> ```kubectl get nodes```
NAME             STATUS   ROLES           AGE     VERSION
docker-desktop   Ready    control-plane   4m14s   v1.31.4
PS C:\Users\nreka\vscodedevops\FHIR-Google-Cloud-API> ```kubectl get deployments```
NAME                          READY   UP-TO-DATE   AVAILABLE   AGE
fhir-restassured-deployment   0/2     2            0           77s
PS C:\Users\nreka\vscodedevops\FHIR-Google-Cloud-API>```kubectl get services```
NAME               TYPE           CLUSTER-IP       EXTERNAL-IP   PORT(S)        AGE
kubernetes         ClusterIP      10.96.0.1        <none>        443/TCP        4m29s
restassured-ssvc   LoadBalancer   10.107.129.191   localhost     80:30143/TCP   84s
PS C:\Users\nreka\vscodedevops\FHIR-Google-Cloud-API> ```kubectl get pods```
NAME                                           READY   STATUS             RESTARTS   AGE
fhir-restassured-deployment-58c7b4d545-fgx9p   0/1     ImagePullBackOff   0          91s
fhir-restassured-deployment-58c7b4d545-jvn6t   0/1     ImagePullBackOff   0          91s
PS C:\Users\nreka\vscodedevops\FHIR-Google-Cloud-API> ```kubectl get svc restassured-ssvc```
NAME               TYPE           CLUSTER-IP       EXTERNAL-IP   PORT(S)        AGE
restassured-ssvc   LoadBalancer   10.107.129.191   localhost     80:30143/TCP   2m14s

## Contact
You can connect with me on [LinkedIn] https://www.linkedin.com/in/reka-srimurugan-040296252/