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
filename	WEB3EquityOffFloorProductListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����O���������ꗗ�T�[�r�X(WEB3EquityOffFloorProductListService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 �X�� (SRA) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;

/**
 * �i����O���������ꗗ�T�[�r�X�j�B<BR>
 * <BR>
 * ����O���������ꗗ�T�[�r�X�C���^�t�F�[�X�B
 * @@version 1.0
 */
public interface WEB3EquityOffFloorProductListService extends WEB3BusinessService 
{
   /**
    * (execute)�B<BR>
    * <BR>
    * ����O���������ꗗ�T�[�r�X���������{����B<BR>
    * <BR>
    * @@param l_request - ���N�G�X�g�f�[�^
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@throws webbroker3.common.WEB3BaseException
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@