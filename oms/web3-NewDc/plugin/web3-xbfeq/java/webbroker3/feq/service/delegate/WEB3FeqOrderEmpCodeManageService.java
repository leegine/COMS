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
filename	WEB3FeqOrderEmpCodeManageService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������^�p�R�[�h�̔ԃT�[�r�X(WEB3FeqOrderEmpCodeManageService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  䈋�(���u) �V�K�쐬
                   2005/07/26 �����F(���u) ���r���[
*/
package webbroker3.feq.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMarket;


/**
 * (�O�������^�p�R�[�h�̔ԃT�[�r�X) <BR>
 * �O�������^�p�R�[�h�̔ԃT�[�r�X�C���^�t�F�C�X
 * @@author 䈋�
 * @@version 1.0 
 */
public interface WEB3FeqOrderEmpCodeManageService extends Service 
{
    
    /**
     * (get�V�K�^�p�R�[�h) <BR>
     * �^�p�R�[�h�������̔Ԃ���B
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strFeqOrderEmpDiv - (�^�p�R�[�h�s�ꎯ�ʋ敪)
     * @@param l_datBizDate - (������)
     * @@return String
     * @@roseuid 42846824012B
     */
    public String getNewEmpCode(String l_strInstitutionCode, String l_strFeqOrderEmpDiv, Date l_datBizDate)
        throws WEB3BaseException;
    
    /**
     * (get�V�K�^�p�R�[�h) <BR>
     * �^�p�R�[�h�������̔Ԃ���B
     * @@param l_market - (�s��) <BR>
     * �s��I�u�W�F�N�g
     * @@param l_datBizDate - (������)
     * @@return String
     * @@roseuid 42846824013B
     */
    public String getNewEmpCode(WEB3GentradeMarket l_market, Date l_datBizDate) throws WEB3BaseException;
}
@
