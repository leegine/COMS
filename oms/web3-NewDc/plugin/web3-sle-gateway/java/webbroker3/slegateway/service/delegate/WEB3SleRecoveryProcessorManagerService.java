head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.02.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleRecoveryProcessorManagerService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 * File Name        : WEB3SleRecoveryProcessorManagerService�N���X
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2006/05/26 ��(FLJ) �V�K�쐬
 */
package webbroker3.slegateway.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.slegateway.message.WEB3ProcessSleRecoveryRequest;


/**
 * ���J�o���[�����C���^�t�F�[�X
 * 
 * @@author  ��(FLJ)
 * @@version 1.0
 */
public interface WEB3SleRecoveryProcessorManagerService extends WEB3BusinessService
{
    /**
     * �����ɂ��A�����̏��������s��
     * @@param request �������b�Z�[�W
     */
    public  void initService(WEB3ProcessSleRecoveryRequest request);
    
    /**
     * ���J�o���[���������s
     */
    public  void startProcessor();

}
@
