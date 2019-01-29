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
filename	WEB3FeqMailSenderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������[�����M�T�[�r�X(WEB3FeqMailSenderService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  䈋�(���u) �V�K�쐬
                   2005/07/26 �����F(���u) ���r���[
*/
package webbroker3.feq.service.delegate;

import webbroker3.common.WEB3BaseException;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;


/**
 * (�O���������[�����M�T�[�r�X) <BR>
 * �O���������[�����M�T�[�r�X�C���^�t�F�C�X
 * @@author 䈋�
 * @@version 1.0 
 */
public interface WEB3FeqMailSenderService extends Service 
{
    
    /**
     * (create�V�K����Mail) <BR>
     * �V�K�����̓��e���A���[�����M�e�[�u���A<BR>
     * ���[�����M�g���e�[�u���ɓo�^����B<BR>
     * @@param l_feqOrderUnit - (�����P��)
     * @@roseuid 4295F45603E7
     */
    public void createNewOrderMail(FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException;
    
    /**
     * (create��������Mail) <BR>
     * ���������̓��e���A���[�����M�e�[�u���A <BR>
     * ���[�����M�g���e�[�u���ɓo�^����B <BR>
     * @@param l_feqOrderUnit - (�����P��)
     * @@roseuid 4299952E0161
     */
    public void createChangeOrderMail(FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException;
    
    /**
     * (create�������Mail) <BR>
     * ��������̓��e���A���[�����M�e�[�u���A <BR>
     * ���[�����M�g���e�[�u���ɓo�^����B <BR>
     * @@param l_feqOrderUnit - (�����P��)
     * @@roseuid 429995A401A0
     */
    public void createCancelOrderMail(FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException;
}
@
