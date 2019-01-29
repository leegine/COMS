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
filename	WEB3EquityOrderCarryOverService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�z�T�[�r�X(WEB3EquityOrderCarryOverService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 羐� (���u) �V�K�쐬
                   2004/09/14 ���C�g (���u) �C��
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

import webbroker3.gentrade.WEB3GentradeMainAccount;

/**
 * �i�����J�z�T�[�r�X�C���^�t�F�[�X�j�B
 * @@version 1.0
 */
public interface WEB3EquityOrderCarryOverService extends WEB3BackBusinessService 
{ 
      
    /**
     * �����J�z�T�[�r�X���������{����
     * @@param l_request - ���N�G�X�g
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4137CDD90324
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
    
    /**
     * (�ڋq�P�ʌJ�z���s)<BR>
     * �����J�z�������s���B
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g�B
     * @@roseuid 411ABEF002FB
     */
    public void execCarryOverForAccount(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException;
}
@
