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
filename	WEB3MarginSwapOrderNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n�����ʒm�T�[�r�X�C���^�t�F�[�X(WEB3MarginSwapOrderNotifyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/24 �X�� (SRA) �V�K�쐬
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * �i�M�p����������n�����ʒm�T�[�r�X�j�B<BR>
 * <BR>
 * �M�p��������ʒm�T�[�r�X�C���^�t�F�[�X
 * @@version 1.0
 */
public interface WEB3MarginSwapOrderNotifyService extends WEB3BackBusinessService 
{
    
    /**
     * (execute)�B<BR>
     * <BR>
     * �M�p����������n�����ʒm�T�[�r�X���������{����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
