head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOrderAcceptService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright       : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name       :�敨OP������t�T�[�r�X�C���^�[�t�F�C�X(WEB3IfoOrderAcceptService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/21   ���Ō� (Sinocom) �V�K�쐬 
              001: 2004/07/28  ���Ō� (Sinocom) �Ή��ڍא݌v�o�b�O 
*/
package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (�敨OP������t�T�[�r�X)<BR>
 * �敨OP������t�T�[�r�X�C���^�[�t�F�C�X<BR>
 */
public interface WEB3IfoOrderAcceptService extends WEB3BackBusinessService 
{
    
    /**
     * �敨OP������t�T�[�r�X���������{����B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.ifo.message.WEB3IfoOrderAcceptResponse
     * @@throws WEB3BaseException
     * @@roseuid 4057CEC4010C
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
