head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.54.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformHostReqOrderNumberManageService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A���Ǘ����ʃR�[�h�̔ԃT�[�r�X(WEB3InformHostReqOrderNumberManageService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 䈋� (���u) �V�K�쐬
*/
package webbroker3.inform.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;


/**
 * (�A���Ǘ����ʃR�[�h�̔ԃT�[�r�X)<BR>
 * �A���Ǘ����ʃR�[�h�̔ԃT�[�r�X�C���^�[�t�F�C�X
 */
public interface WEB3InformHostReqOrderNumberManageService extends Service 
{
    
    /**
     * (get�V�K���ʃR�[�h)<BR>
     * �e��A���̎��ʃR�[�h�������̔Ԃ���B
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * �،���ЃR�[�h
     * 
     * @@param l_strInformDiv - (�A�����)
     * �A�����
     * 
     * @@return String
     * @@roseuid 41BD561400A1
     */
    public String getNewOrderRequestCode(String l_strInstitutionCode, String l_strInformDiv) throws WEB3BaseException;
}
@
