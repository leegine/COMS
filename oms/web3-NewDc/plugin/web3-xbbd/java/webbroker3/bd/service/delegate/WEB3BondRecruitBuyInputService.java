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
filename	WEB3BondRecruitBuyInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������/���t���̓T�[�r�X(WEB3BondRecruitBuyInputService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/08 ������ (���u) �V�K�쐬
*/

package webbroker3.bd.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (������/���t���̓T�[�r�X)<BR>
 * ������/���t���̓T�[�r�X<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public interface WEB3BondRecruitBuyInputService extends WEB3BusinessService 
{
    
    /**
     * ������/���t���̓T�[�r�X���������{����B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44C452560075
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
