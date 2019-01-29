head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M������tUnitService(WEB3MutualOrderAcceptUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ����  (���u) �V�K�쐬
                   2004/08/24 ����� (���u) ���r���[
*/
package webbroker3.mf.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.mf.WEB3MutualFundAcceptConfirmInterceptor;

/**
 * (���M������tUnitService)<BR>
 * ���M������t�P���T�[�r�X�C���^�t�F�[�X
 * �����P�����Ƃ̎�t���������{����B<BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public interface WEB3MutualOrderAcceptUnitService extends Service 
{

    /**
     * ���M������t���������������Ȃ��B<BR>
     * @@param l_mutualFundOrderUnit - ���M�����P�� <BR>
     * @@param l_mutualFundAcceptConfirmInterceptor - ���M��t�m��C���^�Z�v�^<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 408887A103B8
     */
    public void notifyOrderAcceptComplete(
        MutualFundOrderUnit l_mutualFundOrderUnit,
        WEB3MutualFundAcceptConfirmInterceptor l_mutualFundAcceptConfirmInterceptor)
        throws WEB3BaseException;

    /**
     * ���M������t���s�����������Ȃ��B<BR>
     * @@param l_mutualFundOrderUnit - ���M�����P�� <BR>
     * @@param l_mutualFundAcceptConfirmInterceptor - ���M��t�m��C���^�Z�v�^<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 408887A103BA
     */
    public void notifyOrderAcceptFail(
        MutualFundOrderUnit l_mutualFundOrderUnit,
        WEB3MutualFundAcceptConfirmInterceptor l_mutualFundAcceptConfirmInterceptor)
        throws WEB3BaseException;
}
@
