head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleConnectionStatusEnum.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SleBasedMarketAdapterBasePlugin�N���X
Author Name      : Daiwa Institute of Research
Revision History : 2006/05/15 �� �V�K�쐬
*/
package webbroker3.slebase.enums;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;

/**
 * SLE�R�l�N�^��SLE�����G���W���Ԃ̐ڑ���Ԃ��`����
 * 
 */
public class SleConnectionStatusEnum extends Enumerated
{
    
    /**0�FSLE�G���W���֐ڑ������� */
    public static final SleConnectionStatusEnum CONNECTION_LOSE = new SleConnectionStatusEnum(0,
            "CONNECTION_LOSE");

    /**1�FSLE�G���W���֍Đڑ����� */
    public static final SleConnectionStatusEnum RECONNECTION_SUCCESS =  new SleConnectionStatusEnum(1,
            "RECONNECTION_SUCCESS");
    
    /**2�FSLE�R�l�N�^����ɋN��*/
    public static final SleConnectionStatusEnum START_SUCCESS   =  new SleConnectionStatusEnum(2,
            "START_SUCCESS");

    /**3�FSLE�R�l�N�^��~   */
    public static final SleConnectionStatusEnum CONNECTION_STOP =  new SleConnectionStatusEnum(3,
            "CONNECTION_STOP");
    
    public SleConnectionStatusEnum(int i, String s)
    {
        super(i, s);    
    }
    
    /**
     * �C�ӂ̃G�i���̐����l��`����������N���X�ł��B�����l���`���邱�Ƃɂ��A 
     * ���̃N���X��switch���ŗe�Ղɗ��p���邱�Ƃ��ł��܂��B
     */
    public static class IntValues {

        //~ Static fields/initializers ---------------------------------------------

        /**0�FSLE�G���W���֐ڑ������� */
        public static final int CONNECTION_LOSE = 0;

        /**1�FSLE�G���W���֍Đڑ����� */
        public static final int RECONNECTION_SUCCESS = 1;
        
        /**2�FSLE�R�l�N�^����ɋN��*/
        public static final int START_SUCCESS   = 2;

        /**3�FSLE�R�l�N�^��~   */
        public static final int CONNECTION_STOP = 3;
        
    }    
    

}
@
