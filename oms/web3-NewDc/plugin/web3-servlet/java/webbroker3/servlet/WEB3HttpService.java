head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.28.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d956928;
filename	WEB3HttpService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3HttpService�N���X(WEB3HttpService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/09/29 �R�c�@@��i(FLJ) �V�K�쐬
*/
package webbroker3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * HttpRequest��HttpResponse���p�����[�^�Ƃ��Ď󂯎��A
 * �������s��Service����������C���^�[�t�F�[�X
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3HttpService extends Service
{
    
    /**
     * ���̃T�[�r�X�Ɋ֘A�t����ꂽHTTP���N�G�X�g����������B
     * 
     * @@param request �����Ώۂ�Http���N�G�X�g
     * @@param response �������ʂƂ��ĕԂ����Http���X�|���X
     * @@throws ServletException
     * @@throws IOException
     */
    public void execute(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException;

}
@
