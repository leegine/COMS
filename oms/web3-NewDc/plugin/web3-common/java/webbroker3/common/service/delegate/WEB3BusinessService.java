head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BusinessService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@//�\�[�X �t�@@�C��: D:\\WEBBROKER3-��a\\�ڍא݌v\\src\\webbroker3\\common\\service\\delegate\\WEB3BusinessService.java

package webbroker3.common.service.delegate;

import webbroker3.common.*;
import webbroker3.common.message.*;
import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * �Ɩ��A�v���P�[�V�����i��菈���j�̋Ɩ����W�b�N�̃C���^�t�F�[�X�B<BR>
 * <BR>
 * @@author �����@@���F(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.kernel.boot.Service
 */
public interface WEB3BusinessService extends Service 
{
   
   /**
    * �����ŗ^����ꂽ���N�G�X�g����ɋƖ��������s���A�������ʂ����X�|���X�ɐݒ�<BR>
    * ���ĕԂ��B<BR>
    * <BR>
    * @@param l_request ���N�G�X�g
    * @@return �������ʂ��ݒ肳�ꂽ���X�|���X@@throws 
    * webbroker3.common.WEB3BaseException
    * @@roseuid 403494170141
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
