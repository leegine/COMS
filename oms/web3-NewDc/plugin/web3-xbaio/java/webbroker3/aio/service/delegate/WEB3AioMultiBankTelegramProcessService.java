head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.15.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioMultiBankTelegramProcessService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �}���`�o���N�d�������T�[�r�X�C���^�[�t�F�C�X(WEB3AioMultiBankTelegramProcessService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 �����(���u) �V�K�쐬
                   2004/10/25 ���� (���u) ���r���[     
*/

package webbroker3.aio.service.delegate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webbroker3.servlet.WEB3HttpService;

/**
 * (�}���`�o���N�d�������T�[�r�X)<BR>
 * �}���`�o���N�d�������T�[�r�X�C���^�[�t�F�C�X
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public interface WEB3AioMultiBankTelegramProcessService extends WEB3HttpService 
{
    
    /**
     * �}���`�o���N�d���������s���B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@param l_response - ���X�|���X�f�[�^
     * @@roseuid 41255C2201AA
     */
    public void execute(HttpServletRequest l_request, HttpServletResponse l_response)
        throws ServletException, IOException;
}
@
