head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.02.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleSendqProcessorManagerService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SleSendqProcessorManagerService�N���X
Author Name      : Daiwa Institute of Research
Revision History : 2006/04/24 �� �V�K�쐬
�@@�@@�@@�@@�@@�@@�@@�@@�@@ 2006/09/20 �� WEB3�������j�ɂ��킹
*/
package webbroker3.slegateway.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * SEND_Q���M�Ǘ��N���X�̃C���^�t�F�[�X
 */
public interface WEB3SleSendqProcessorManagerService extends WEB3BusinessService{
	/**
	  * send_q���M�Ǘ����������{����B<BR>
	  * @@param l_request - (���N�G�X�g�f�[�^)<BR>
	  * ���N�G�X�g�f�[�^<BR>
	  * 
	  * @@return WEB3GenResponse
	  * @@throws WEB3BaseException
	  */
	 public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
