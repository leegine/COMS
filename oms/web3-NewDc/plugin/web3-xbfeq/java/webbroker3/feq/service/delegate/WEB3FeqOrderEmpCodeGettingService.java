head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderEmpCodeGettingService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������^�p�R�[�h�擾�T�[�r�X�iWEB3FeqOrderEmpCodeGettingService.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/03 ���g (���u) �V�K�쐬 ���f��No.501
*/
package webbroker3.feq.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;

/**
 * (�O�������^�p�R�[�h�擾�T�[�r�X) <BR>
 * �O�������^�p�R�[�h�擾�T�[�r�X�C���^�t�F�C�X<BR>
 * @@author ���g
 * @@version 1.0 
 */
public interface WEB3FeqOrderEmpCodeGettingService extends Service
{

    /**
     * (getPREFIX)<BR>
     * �^�p�R�[�h�̍�2�����擾����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getPREFIX(String l_strInstitutionCode) throws WEB3BaseException;

    /**
     * (get�^�p�R�[�h)<BR>
     * �V���́u�^�p�R�[�h�v��������擾���ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strEmpCode - (�^�p�R�[�h)<BR>
     * �^�p�R�[�h<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getEmpCode(String l_strInstitutionCode, String l_strEmpCode) throws WEB3BaseException;
}
@
