head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBookValuePriceRegistService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������뉿�P���o�^�T�[�r�X(WEB3FeqBookValuePriceRegistService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 ���� (���u) �V�K�쐬   
                 : 2005/08/03 �A�C��(���u) ���r���[       
*/

package webbroker3.feq.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�O�������뉿�P���o�^�T�[�r�X)<BR>
 * �O�������뉿�P���o�^�T�[�r�X�C���^�t�F�C�X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public interface WEB3FeqBookValuePriceRegistService extends WEB3BusinessService 
{
    
    /**
     * �O�������뉿�P���o�^�T�[�r�X���s���B
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A9375202CB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException;
}
@
