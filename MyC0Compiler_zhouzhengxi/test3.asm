Stack segment para Stack
dw 100h dup(?);
Stack ends

data segment
const_number dw ?;
num dw ?;
function dw ?;
a dw ?;
b dw ?;
e dw ?;
i dw ?;
str24 db 'a*b=$';
str26 db 'num=$';
str28 db 'funtion1()=$';
prev_len EQU 100;
temp_len EQU 10;
prev dw ?;
dw prev_len dup(?);
temp_prev dw ?;
dw temp_len dup(?);
display db 'this is an example of simple compiler by zhouzhengxi 36060320.$'
data ends

code segment
      assume cs:code,ds:data,ss:Stack

function_pro proc
      mov ax,temp_prev+3;
      mov prev+2,ax;Ϊprev+2��ֵΪtemp_prev+3
      mov ax,prev+2;
      mov function,ax;Ϊfunction��ֵΪprev+2
      ret
function_pro endp

main proc far
start:push ds;
      mov ax,0;
      push ax;
      mov ax,data;
      mov ds,ax;

      mov ax,2;
      mov a,ax;Ϊa��ֵΪ2
      mov ax,6;
      mov b,ax;Ϊb��ֵΪ6
      mov ax,1;
      mov i,ax;Ϊi��ֵΪ1
      mov ax,a;
      mov cx,b;
      mul cx;
      mov bx,ax;���˷���ֵ��bx
      mov ax,bx;
      mov e,ax;Ϊe��ֵΪbx
      mov ax,0;
      mov num,ax;Ϊnum��ֵΪ0
lab13:
      mov ax,i;
      sub ax,3;
      mov dx,ax;��������ֵ��dx
      mov ax,dx;
      cmp ax,0;
      jne  lab17;��ת��17
lab15:
      mov ax,0;
      mov dx,ax;Ϊdx��ֵΪ0
      jmp lab18;��ת��18
lab17:
      mov ax,1;
      mov dx,ax;Ϊdx��ֵΪ1
lab18:
      mov ax,dx;
      cmp ax,0;
      jne  lab20;��ת��20
lab19:
      jmp lab25;��ת��25
lab20:
      mov ax,num;
      add ax,i;
      mov bx,ax;���ӷ���ֵ��bx
      mov ax,bx;
      mov num,ax;Ϊnum��ֵΪbx
      mov ax,i;
      add ax,1;
      mov dx,ax;���ӷ���ֵ��dx
      mov ax,dx;
      mov i,ax;Ϊi��ֵΪdx
      jmp lab13;��ת��13
lab25:
      mov dx,offset str24;
      mov ah,09h;
      int 21h;����ַ�������str24
      mov ax,e;
      mov bx,10;
      mov cx,5;
      call PrintfNum;����������ʽ�ӽ��
      mov dx,offset str26;
      mov ah,09h;
      int 21h;����ַ�������str26
      mov ax,num;
      mov bx,10;
      mov cx,5;
      call PrintfNum;����������ʽ�ӽ��
      mov dx,offset str28;
      mov ah,09h;
      int 21h;����ַ�������str28
      mov ax,14;
      mov temp_prev+3,ax;Ϊtemp_prev+3��ֵΪ14
      call function_pro;
      mov ax,function;
      mov bx,10;
      mov cx,5;
      call PrintfNum;����������ʽ�ӽ��
lab71:
      mov ah,9h;
      mov dx,offset display;
      int 21h;
      mov ax,4c00h;
      int 21h;
main endp

PrintfNum  proc
LP1:  xor  dx,dx;
      div  bx;
      or   dl,30h;
      push dx;
      loop LP1;
      mov  cx,5;
LP2:  pop  dx;
      mov  ah,2;
      int  21h;
      loop LP2;
      ret;
PrintfNum endp;����������ʽ

code ends
end start
