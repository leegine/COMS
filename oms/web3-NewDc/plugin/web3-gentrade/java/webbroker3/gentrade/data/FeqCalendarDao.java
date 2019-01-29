head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.21.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FeqCalendarDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.gentrade.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link FeqCalendarDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link FeqCalendarRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
 * �N���C�A���g�R�[�h�ɂ����ĕK�v�Ƃ���鋤�ʂ̃f�[�^�I�y���[�V�������������Ă��܂��B 
 * <p> 
 *     <li> �V�������R�[�h�ɑ΂���ӂ̎�L�[�l�܂��̓I�u�W�F�N�g���쐬 </li> 
 *     <li> �O���L�[���烌�R�[�h������ </li> 
 *     <li> �O���L�[�̊֌W�ɂ���I�u�W�F�N�g������ </li> 
 *     <li> �C���f�b�N�X���������̃f�[�^�x�[�X�X�L�[�}�ɃN�G�������s </li> 
 * <p> 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.xtrade.kernel.data.DataAccessObject 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FeqCalendarPK 
 * @@see FeqCalendarRow 
 */
public class FeqCalendarDao extends DataAccessObject {


  /** 
   * ����{@@link FeqCalendarDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private FeqCalendarRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link FeqCalendarRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link FeqCalendarDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link FeqCalendarDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link FeqCalendarRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FeqCalendarRow )
                return new FeqCalendarDao( (FeqCalendarRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FeqCalendarRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FeqCalendarRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link FeqCalendarRow}�I�u�W�F�N�g 
    */
    protected FeqCalendarDao( FeqCalendarRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link FeqCalendarRow}�I�u�W�F�N�g���擾���܂��B
   */
    public FeqCalendarRow getFeqCalendarRow() {
        return row;
    }


  /** 
   * �w���{@@link FeqCalendarRow}�I�u�W�F�N�g����{@@link FeqCalendarDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link FeqCalendarRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link FeqCalendarDao}�擾�̂��߂Ɏw���{@@link FeqCalendarRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link FeqCalendarDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static FeqCalendarDao forRow( FeqCalendarRow row ) throws java.lang.IllegalArgumentException {
        return (FeqCalendarDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FeqCalendarRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link FeqCalendarRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link FeqCalendarPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link FeqCalendarParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FeqCalendarRow.TYPE );
    }


  /** 
   * {@@link FeqCalendarRow}����ӂɓ��肷��{@@link FeqCalendarPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link FeqCalendarRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link FeqCalendarParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link FeqCalendarPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static FeqCalendarPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link FeqCalendarRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_bizDate �����Ώۂł���p_bizDate�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FeqCalendarRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FeqCalendarRow findRowByPk( String p_institutionCode, String p_marketCode, java.sql.Timestamp p_bizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqCalendarPK pk = new FeqCalendarPK( p_institutionCode, p_marketCode, p_bizDate );
        return findRowByPk( pk );
    }


  /** 
   * �w���FeqCalendarPK�I�u�W�F�N�g����{@@link FeqCalendarRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����FeqCalendarPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FeqCalendarRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FeqCalendarRow findRowByPk( FeqCalendarPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FeqCalendarRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,java.sql.Timestamp)}�����{@@link #forRow(FeqCalendarRow)}���g�p���Ă��������B 
   */
    public static FeqCalendarDao findDaoByPk( String p_institutionCode, String p_marketCode, java.sql.Timestamp p_bizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqCalendarPK pk = new FeqCalendarPK( p_institutionCode, p_marketCode, p_bizDate );
        FeqCalendarRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(FeqCalendarPK)}�����{@@link #forRow(FeqCalendarRow)}���g�p���Ă��������B 
   */
    public static FeqCalendarDao findDaoByPk( FeqCalendarPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqCalendarRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


      // (this object has no foreign keys)


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_marketCode, p_bizDate, and �ɂĎw��̒l�����ӂ�{@@link FeqCalendarRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_bizDate �����Ώۂł���p_bizDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_marketCode, p_bizDate, and �̒l�ƈ�v����{@@link FeqCalendarRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static FeqCalendarRow findRowByInstitutionCodeMarketCodeBizDate( String p_institutionCode, String p_marketCode, java.sql.Timestamp p_bizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqCalendarRow.TYPE,
            "institution_code=? and market_code=? and biz_date=?",
            null,
            new Object[] { p_institutionCode, p_marketCode, p_bizDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqCalendarRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqCalendarDao.findRowsByInstitutionCodeMarketCodeBizDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeMarketCodeBizDate(String, String, java.sql.Timestamp)}�����{@@link #forRow(FeqCalendarRow)}���g�p���Ă��������B 
   */
    public static FeqCalendarDao findDaoByInstitutionCodeMarketCodeBizDate( String p_institutionCode, String p_marketCode, java.sql.Timestamp p_bizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeMarketCodeBizDate( p_institutionCode, p_marketCode, p_bizDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
