head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.28.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d956928;
filename	WEB3HttpServiceMappings.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3HttpServiceMappings�N���X(WEB3HttpServiceMappings.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/10/01 �R�c�@@��i(FLJ) �V�K�쐬
*/
package webbroker3.servlet;

import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * HTTP���N�G�X�g���M����URL�Ŏw�肷��T�[�r�X���ƁA
 * ����HTTP���N�G�X�g����������T�[�r�X�̃}�b�s���O��ێ�����N���X<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3HttpServiceMappings extends Service
{
    
    /**
     * �T�[�r�X����WEB3HttpService�̃}�b�s���O��o�^����B
     * 
     * @@param serviceName �T�[�r�X��
     * @@param serviceInterface WEB3HttpService�̃C���^�[�t�F�[�X
     */
    public void addService(String l_serviceName, Class l_serviceInterface);
    
    /**
     * �w�肳�ꂽ�T�[�r�X���Ƀ}�b�s���O���ꂽWEB3HttpService���擾����B
     * 
     * @@param serviceName �T�[�r�X
     * @@return �w�肳�ꂽ�T�[�r�X���Ƀ}�b�s���O���ꂽWEB3HttpService
     */
    public WEB3HttpService getService(String l_serviceName);
    
    
    /**
     * �f�t�H���g�Ŏg�p����WEB3HttpService��o�^����B
     * 
     * @@param serviceInterface �f�t�H���g�Ŏg�p����WEB3HttpService
     */
    public void setDefaultService(Class l_serviceInterface);
    
    /**
     * �f�t�H���g�Ŏg�p����WEB3HttpService���擾����B
     * 
     * @@return
     */
    public WEB3HttpService getDefaultService();
    
    /**
     * �w�肳�ꂽ�T�[�r�X����WEB3HttpService�̃}�b�s���O���폜����B
     * 
     * @@param serviceName �T�[�r�X��
     */
    public void removeService(String l_serviceName);
    
}@
