head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeCloseMarginService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (�M�p��������ԍϓ��̓T�[�r�XImpl)
                 : �M�p��������ԍϓ��̓T�[�r�X�����N���X
                 : (WEB3MarginChangeCloseMarginInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/30 li-songfeng (���u) �V�K�쐬
                   2005/01/05 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * �i�M�p��������ԍσT�[�r�X�j�B<BR>
 * <BR>
 * �M�p��������ԍσT�[�r�X�C���^�t�F�[�X<BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j
 * @@version 1.0
 */
public interface WEB3MarginChangeCloseMarginService extends WEB3BusinessService 
{
    
    /**
     * �M�p��������ԍσT�[�r�X���������{����
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4058284B02EB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
