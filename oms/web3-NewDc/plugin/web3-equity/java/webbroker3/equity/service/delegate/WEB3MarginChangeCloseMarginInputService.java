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
filename	WEB3MarginChangeCloseMarginInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p��������ԍϓ��̓T�[�r�X
                 : (WEB3MarginChangeCloseMarginInputService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �����a��(SRA) JavaDoc�C��
*/
//�\�[�X �t�@@�C��: D:\\WEB3\\Margin\\release\\javaSource\\webbroker3\\margin\\service\\delegate\\WEB3MarginCloseMarginChangeInputService.java

package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * �i�M�p��������ԍϓ��̓T�[�r�X�j�B<BR>
 * <BR>
 * �M�p��������ԍϓ��̓T�[�r�X�C���^�t�F�[�X
 * @@version 1.0
 */
public interface WEB3MarginChangeCloseMarginInputService extends WEB3BusinessService 
{
    
    /**
     * �M�p��������ԍϓ��̓T�[�r�X���������{����B
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 407CB24902CB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@