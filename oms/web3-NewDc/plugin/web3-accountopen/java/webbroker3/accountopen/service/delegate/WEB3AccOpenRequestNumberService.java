head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.44.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenRequestNumberService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݎ��ʃR�[�h�̔ԃT�[�r�X(WEB3AccOpenRequestNumberService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 �s�p (���u) �V�K�쐬
*/

package webbroker3.accountopen.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;


/**
 * (�����J�ݎ��ʃR�[�h�̔ԃT�[�r�X)<BR>
 * �����J�ݎ��ʃR�[�h�̔ԃT�[�r�X�C���^�t�F�C�X<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */

public interface WEB3AccOpenRequestNumberService extends Service 
{
    
    /**
     * (get�V�K���ʃR�[�h)<BR>
     * �����J�݌����q�̎��ʃR�[�h�������̔Ԃ���B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h�B
     * @@return String
     * @@roseuid 418720D9037E
     */
    public String getNewRequestNumber(String l_strInstitutionCode) throws WEB3BaseException;
}
@
