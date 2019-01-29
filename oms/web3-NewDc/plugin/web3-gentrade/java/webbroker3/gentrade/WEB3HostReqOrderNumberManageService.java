head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3HostReqOrderNumberManageService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : ���ʔԍ��̔ԃC���^�[�t�F�[�X(WEB3HostReqOrderNumberManageService.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/17 �� �� (���u) ������ύX
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;

/**
 * �i���ʔԍ��̔ԃC���^�[�t�F�[�X�j<BR>
 * <BR>
 * �������ʔԍ����̔Ԃ���T�[�r�X�C���^�[�t�F�[�X<BR>
 */
public interface WEB3HostReqOrderNumberManageService extends Service
{

    /**
     * �������ʔԍ����擾����B<BR>
     * <BR>
     * @@param l_strInstitutionCode�@@�،���ЃR�[�h
     * @@param l_strBranchCode�@@���X�R�[�h
     * @@param l_product_type�@@�����^�C�v
     * @@return�@@�������ʔԍ�
     * @@roseuid 40349664021C
     */
    public String getNewNumber(
        String l_strInstitutionCode,
        String l_strBranchCode,
        ProductTypeEnum l_product_type) throws WEB3BaseException;
}
@
