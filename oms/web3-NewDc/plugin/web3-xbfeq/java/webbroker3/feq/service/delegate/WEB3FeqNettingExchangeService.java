head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqNettingExchangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : �O�������בփl�b�e�B���O�T�[�r�X(WEB3FeqNettingExchangeService.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/09/08 �Ԑi (���u) �V�K�쐬 ���f��No.549
*/

package webbroker3.feq.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (�O�������בփl�b�e�B���O�T�[�r�X)<BR>
 * �O�������בփl�b�e�B���O�T�[�r�X�C���^�[�t�F�C�X <BR>
 * <BR>
 * @@author �Ԑi
 * @@version 1.0
 */
public interface WEB3FeqNettingExchangeService extends WEB3BackBusinessService 
{
    /**
     * �O�������בփl�b�e�B���O�T�[�r�X�������s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4214980A032E
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
