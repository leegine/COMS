head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeOpenMarginService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p��������V�K���T�[�r�X(WEB3MarginChangeOpenMarginService.java)
Author Name      : 2004/10/10 Ḗ@@��(���u) �V�K�쐬
                   2005/01/05 ����   (SRA) JavaDoc�C��
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * �i�M�p��������V�K���T�[�r�X�j�B<BR>
 * <BR>
 * �M�p��������V�K���T�[�r�X�C���^�t�F�[�X
 *�i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j
 * @@author �@@��
 * @@version 1.0
 */
public interface WEB3MarginChangeOpenMarginService extends WEB3BusinessService 
{
    
    /**
     * �M�p��������V�K���T�[�r�X���������{����B
     * @@param l_request - ���N�G�X�g
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40581CFF01F1
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
