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
filename	WEB3MarginSwapMarginAcceptService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n��t�T�[�r�X(WEB3MarginSwapMarginAcceptService.java)
Author Name      : 2004/10/8 Ḗ@@��(���u) �V�K�쐬
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * �i�M�p����������n��t�T�[�r�X�j�B<BR>
 * <BR>
 * �M�p����������n��t�T�[�r�X�C���^�t�F�[�X<BR>
 *�i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j
 * @@author �@@��
 * @@version 1.0
 */
public interface WEB3MarginSwapMarginAcceptService extends WEB3BackBusinessService 
{
    
    /**
     * �M�p����������n��t�T�[�r�X���������{����B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4057ADC6015A
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
