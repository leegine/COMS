head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.34.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOrderActionUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�\�����𖾍׍쐬�T�[�r�X�C���^�t�F�C�X(WEB3IpoOrderActionUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���� (���u) �V�K�쐬
*/

package webbroker3.ipo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.message.WEB3IPODemandHistoryUnit;

/**
 * IPO�\�����𖾍׍쐬�T�[�r�X�C���^�t�F�C�X
 *                                                                
 * @@author ����
 * @@version 1.0
 */
public interface WEB3IpoOrderActionUnitService extends Service 
{
    
    /**
     * (createIPO�\�����𖾍�)<BR>
     * IPO�\�����𖾍ׂ��쐬����B
     * @@param l_ipoOrder - (IPO�\��)<BR>
     * IPO�\���I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoOrderActionUnit[]
     * @@roseuid 40EE6A70023C
     */
    public WEB3IPODemandHistoryUnit[] createOrderActionUnit(WEB3IpoOrderImpl l_ipoOrder) throws WEB3BaseException;
}
@
