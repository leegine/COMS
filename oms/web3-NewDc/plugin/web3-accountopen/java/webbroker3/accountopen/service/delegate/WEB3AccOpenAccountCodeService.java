head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenAccountCodeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�݌ڋq�R�[�h�̔ԃT�[�r�X(WEB3AccOpenAccountCodeService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/14 �����F (���u) �V�K�쐬
*/

package webbroker3.accountopen.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;

/**
 * (�����J�݌ڋq�R�[�h�̔ԃT�[�r�X)<BR>
 * �����J�݌ڋq�R�[�h�̔ԃT�[�r�X�C���^�t�F�C�X<BR>
 *   
 * @@author �����F
 * @@version 1.0
 */
public interface WEB3AccOpenAccountCodeService extends Service
{
    /**
     * (get�V�K�ڋq�R�[�h )<BR>
     * �����J�݌����q�̌ڋq�R�[�h�������̔Ԃ���B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountDiv - (�ڋq�敪)<BR>
     * �ڋq�敪<BR>
     * �l�́ADB���C�A�E�g�u�����J�݌ڋq�R�[�h�v���Q�ƁB<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getNewAccountCode(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountDiv) 
        throws WEB3BaseException;
}
@
