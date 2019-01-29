head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOrderNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  (�M�p��������ʒm�ꌏ�T�[�r�X)<BR>
                 :  �M�p��������ʒm�ꌏ�T�[�r�X�C���^�t�F�[�X
                 :  (WEB3MarginOrderNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ������ (���u) �V�K�쐬
                   2005/01/05 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;

import com.fitechlabs.xtrade.kernel.boot.Service;


/**
 * �i�M�p��������ʒm�ꌏ�T�[�r�X�j�B<BR>
 * <BR>
 * �M�p��������ʒm�ꌏ�T�[�r�X�C���^�t�F�[�X<BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j
 * @@version 1.0
 */
public interface WEB3MarginOrderNotifyUnitService extends Service 
{
    
    /**
     * (notify�V�K������)<BR>
     * �V�K�������ʒm���������{����B
     * @@param l_marginOrderInputNotifyAdapter - (�M�p����������͒ʒm�f�[�^�A�_�v�^)<BR>
     * �M�p����������͒ʒm�f�[�^�A�_�v�^�I�u�W�F�N�g
     * @@roseuid 40EA5AA003CF
     */
    public void notifyOpenMarginOrder(
        WEB3MarginOrderNotifyDataAdapter l_marginOrderNotifyDataAdapter)
        throws WEB3BaseException;
    
    /**
     * (notify�ԍϒ���)<BR>
     * �ԍϒ����ʒm���������{����B
     * @@param l_marginOrderInputNotifyAdapter - �M�p����������͒ʒm�f�[�^�A�_�v�^�I�u�W�F�N�g
     * @@roseuid 40EA5AB1012F
     */
    public void notifyCloseMarginOrder(
        WEB3MarginOrderNotifyDataAdapter l_marginOrderNotifyDataAdapter)
        throws WEB3BaseException;
}
@
