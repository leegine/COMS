head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondRecruitBuyProductListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������/���t�����ꗗ�T�[�r�X(WEB3BondRecruitBuyProductListService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/9/5 �s�p (���u) �V�K�쐬 
*/

package webbroker3.bd.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (������/���t�����ꗗ�T�[�r�X)<BR>
 * ������/���t�����ꗗ�T�[�r�X
 *   
 * @@author �s�p
 * @@version 1.0
 */
public interface WEB3BondRecruitBuyProductListService extends WEB3BusinessService 
{
    
    /**
     * ������/���t�����ꗗ�T�[�r�X���������{����B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B740BA0017
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
