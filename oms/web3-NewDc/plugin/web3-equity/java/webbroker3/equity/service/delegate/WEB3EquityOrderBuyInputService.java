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
filename	WEB3EquityOrderBuyInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������t���̓T�[�r�X�C���^�[�t�F�[�X (WEB3EquityOrderBuyInputService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/04/10 �R�w�� (���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * �i�����������t�������̓T�[�r�X�C���^�[�t�F�C�X�j�B
 * @@version 1.0
 */
public interface WEB3EquityOrderBuyInputService extends WEB3BusinessService 
{
   /**
    * (�����������t�������̓T�[�r�X���������{����) <BR>
    * @@param l_request - ���N�G�X�g�f�[�^
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 4062857B029B
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
