head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.47.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQGatewayService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : MQGatewayService��Service�N���X(WEB3MQGatewayService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2003/02/13 �R�c�@@��i(FLJ) �V�K�쐬
*/
package webbroker3.mqgateway;

import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * <p>
 * �A�v���P�[�V��������MQ�ɑ΂��ă��b�Z�[�W�𑗐M����Ƃ���Gateway�ƂȂ�T�[�r�X�B<br>
 * �A�v���P�[�V�����́A�T�[�r�X��send()���\�b�h���Ăяo�����Ƃɂ��AMQ�ɑ΂��ă��b�Z�[�W�𑗐M����B<br>
 * </p>
 * <p>
 * �T�[�r�X�̎g�p���@@�F<br>
 * <code>
 * <pre>
 * WEB3MQMessageSpec spec = // ���M������e;<br>
 * WEB3MQGatewayService gateway =<br>
 *     (WEB3MQGatewayService) Services.getService(WEB3MQGatewayService.class);<br>
 * WEB3MQSendResult result = gateway.send(spec);<br>
 * if (result.isFailedResult()) {<br>
 *     ErrorInfo errorInfo = result.getErrorInfo();<br>
 *     // �G���[����<br>
 * }<br>
 * </pre>
 * </code>
 * </p>
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3MQGatewayService extends Service
{
    /**
     * <p>
     * �p�����[�^�Ŏw�肳�ꂽMQMessageSpec����MQ�ɑ��M���郁�b�Z�[�W�𐶐����A<br>
     * MQ�Ƀ��b�Z�[�W�𑗐M����B<br>
     * </p>

     *      * @@param spec MQ�ɑ��M���郁�b�Z�[�W�̃X�y�b�N
     * @@return ���M����
     */
    public WEB3MQSendResult send(WEB3MQMessageSpec spec);
    
}
@
