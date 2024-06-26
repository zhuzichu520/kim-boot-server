// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: SentBody.proto

package com.chuzi.imsdk.server.model.proto;

public final class SentBodyProto {
  private SentBodyProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface SentBodyOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.chuzi.imsdk.server.model.proto.SentBody)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string key = 1;</code>
     * @return The key.
     */
    java.lang.String getKey();
    /**
     * <code>string key = 1;</code>
     * @return The bytes for key.
     */
    com.google.protobuf.ByteString
        getKeyBytes();

    /**
     * <code>int64 timestamp = 2;</code>
     * @return The timestamp.
     */
    long getTimestamp();

    /**
     * <code>map&lt;string, string&gt; data = 3;</code>
     */
    int getDataCount();
    /**
     * <code>map&lt;string, string&gt; data = 3;</code>
     */
    boolean containsData(
        java.lang.String key);
    /**
     * Use {@link #getDataMap()} instead.
     */
    @java.lang.Deprecated
    java.util.Map<java.lang.String, java.lang.String>
    getData();
    /**
     * <code>map&lt;string, string&gt; data = 3;</code>
     */
    java.util.Map<java.lang.String, java.lang.String>
    getDataMap();
    /**
     * <code>map&lt;string, string&gt; data = 3;</code>
     */
    /* nullable */
java.lang.String getDataOrDefault(
        java.lang.String key,
        /* nullable */
java.lang.String defaultValue);
    /**
     * <code>map&lt;string, string&gt; data = 3;</code>
     */
    java.lang.String getDataOrThrow(
        java.lang.String key);
  }
  /**
   * Protobuf type {@code com.chuzi.imsdk.server.model.proto.SentBody}
   */
  public static final class SentBody extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:com.chuzi.imsdk.server.model.proto.SentBody)
      SentBodyOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use SentBody.newBuilder() to construct.
    private SentBody(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private SentBody() {
      key_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new SentBody();
    }

    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.chuzi.imsdk.server.model.proto.SentBodyProto.internal_static_com_chuzi_imsdk_server_model_proto_SentBody_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    @java.lang.Override
    protected com.google.protobuf.MapField internalGetMapField(
        int number) {
      switch (number) {
        case 3:
          return internalGetData();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuzi.imsdk.server.model.proto.SentBodyProto.internal_static_com_chuzi_imsdk_server_model_proto_SentBody_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody.class, com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody.Builder.class);
    }

    public static final int KEY_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object key_ = "";
    /**
     * <code>string key = 1;</code>
     * @return The key.
     */
    @java.lang.Override
    public java.lang.String getKey() {
      java.lang.Object ref = key_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        key_ = s;
        return s;
      }
    }
    /**
     * <code>string key = 1;</code>
     * @return The bytes for key.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getKeyBytes() {
      java.lang.Object ref = key_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        key_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TIMESTAMP_FIELD_NUMBER = 2;
    private long timestamp_ = 0L;
    /**
     * <code>int64 timestamp = 2;</code>
     * @return The timestamp.
     */
    @java.lang.Override
    public long getTimestamp() {
      return timestamp_;
    }

    public static final int DATA_FIELD_NUMBER = 3;
    private static final class DataDefaultEntryHolder {
      static final com.google.protobuf.MapEntry<
          java.lang.String, java.lang.String> defaultEntry =
              com.google.protobuf.MapEntry
              .<java.lang.String, java.lang.String>newDefaultInstance(
                  com.chuzi.imsdk.server.model.proto.SentBodyProto.internal_static_com_chuzi_imsdk_server_model_proto_SentBody_DataEntry_descriptor, 
                  com.google.protobuf.WireFormat.FieldType.STRING,
                  "",
                  com.google.protobuf.WireFormat.FieldType.STRING,
                  "");
    }
    @SuppressWarnings("serial")
    private com.google.protobuf.MapField<
        java.lang.String, java.lang.String> data_;
    private com.google.protobuf.MapField<java.lang.String, java.lang.String>
    internalGetData() {
      if (data_ == null) {
        return com.google.protobuf.MapField.emptyMapField(
            DataDefaultEntryHolder.defaultEntry);
      }
      return data_;
    }
    public int getDataCount() {
      return internalGetData().getMap().size();
    }
    /**
     * <code>map&lt;string, string&gt; data = 3;</code>
     */
    @java.lang.Override
    public boolean containsData(
        java.lang.String key) {
      if (key == null) { throw new NullPointerException("map key"); }
      return internalGetData().getMap().containsKey(key);
    }
    /**
     * Use {@link #getDataMap()} instead.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, java.lang.String> getData() {
      return getDataMap();
    }
    /**
     * <code>map&lt;string, string&gt; data = 3;</code>
     */
    @java.lang.Override
    public java.util.Map<java.lang.String, java.lang.String> getDataMap() {
      return internalGetData().getMap();
    }
    /**
     * <code>map&lt;string, string&gt; data = 3;</code>
     */
    @java.lang.Override
    public /* nullable */
java.lang.String getDataOrDefault(
        java.lang.String key,
        /* nullable */
java.lang.String defaultValue) {
      if (key == null) { throw new NullPointerException("map key"); }
      java.util.Map<java.lang.String, java.lang.String> map =
          internalGetData().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <code>map&lt;string, string&gt; data = 3;</code>
     */
    @java.lang.Override
    public java.lang.String getDataOrThrow(
        java.lang.String key) {
      if (key == null) { throw new NullPointerException("map key"); }
      java.util.Map<java.lang.String, java.lang.String> map =
          internalGetData().getMap();
      if (!map.containsKey(key)) {
        throw new java.lang.IllegalArgumentException();
      }
      return map.get(key);
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(key_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, key_);
      }
      if (timestamp_ != 0L) {
        output.writeInt64(2, timestamp_);
      }
      com.google.protobuf.GeneratedMessageV3
        .serializeStringMapTo(
          output,
          internalGetData(),
          DataDefaultEntryHolder.defaultEntry,
          3);
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(key_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, key_);
      }
      if (timestamp_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(2, timestamp_);
      }
      for (java.util.Map.Entry<java.lang.String, java.lang.String> entry
           : internalGetData().getMap().entrySet()) {
        com.google.protobuf.MapEntry<java.lang.String, java.lang.String>
        data__ = DataDefaultEntryHolder.defaultEntry.newBuilderForType()
            .setKey(entry.getKey())
            .setValue(entry.getValue())
            .build();
        size += com.google.protobuf.CodedOutputStream
            .computeMessageSize(3, data__);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody)) {
        return super.equals(obj);
      }
      com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody other = (com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody) obj;

      if (!getKey()
          .equals(other.getKey())) return false;
      if (getTimestamp()
          != other.getTimestamp()) return false;
      if (!internalGetData().equals(
          other.internalGetData())) return false;
      if (!getUnknownFields().equals(other.getUnknownFields())) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + KEY_FIELD_NUMBER;
      hash = (53 * hash) + getKey().hashCode();
      hash = (37 * hash) + TIMESTAMP_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getTimestamp());
      if (!internalGetData().getMap().isEmpty()) {
        hash = (37 * hash) + DATA_FIELD_NUMBER;
        hash = (53 * hash) + internalGetData().hashCode();
      }
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }

    public static com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.chuzi.imsdk.server.model.proto.SentBody}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.chuzi.imsdk.server.model.proto.SentBody)
        com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBodyOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuzi.imsdk.server.model.proto.SentBodyProto.internal_static_com_chuzi_imsdk_server_model_proto_SentBody_descriptor;
      }

      @SuppressWarnings({"rawtypes"})
      protected com.google.protobuf.MapField internalGetMapField(
          int number) {
        switch (number) {
          case 3:
            return internalGetData();
          default:
            throw new RuntimeException(
                "Invalid map field number: " + number);
        }
      }
      @SuppressWarnings({"rawtypes"})
      protected com.google.protobuf.MapField internalGetMutableMapField(
          int number) {
        switch (number) {
          case 3:
            return internalGetMutableData();
          default:
            throw new RuntimeException(
                "Invalid map field number: " + number);
        }
      }
      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuzi.imsdk.server.model.proto.SentBodyProto.internal_static_com_chuzi_imsdk_server_model_proto_SentBody_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody.class, com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody.Builder.class);
      }

      // Construct using com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody.newBuilder()
      private Builder() {

      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);

      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        bitField0_ = 0;
        key_ = "";
        timestamp_ = 0L;
        internalGetMutableData().clear();
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuzi.imsdk.server.model.proto.SentBodyProto.internal_static_com_chuzi_imsdk_server_model_proto_SentBody_descriptor;
      }

      @java.lang.Override
      public com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody getDefaultInstanceForType() {
        return com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody.getDefaultInstance();
      }

      @java.lang.Override
      public com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody build() {
        com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody buildPartial() {
        com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody result = new com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.key_ = key_;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.timestamp_ = timestamp_;
        }
        if (((from_bitField0_ & 0x00000004) != 0)) {
          result.data_ = internalGetData();
          result.data_.makeImmutable();
        }
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody) {
          return mergeFrom((com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody other) {
        if (other == com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody.getDefaultInstance()) return this;
        if (!other.getKey().isEmpty()) {
          key_ = other.key_;
          bitField0_ |= 0x00000001;
          onChanged();
        }
        if (other.getTimestamp() != 0L) {
          setTimestamp(other.getTimestamp());
        }
        internalGetMutableData().mergeFrom(
            other.internalGetData());
        bitField0_ |= 0x00000004;
        this.mergeUnknownFields(other.getUnknownFields());
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        if (extensionRegistry == null) {
          throw new java.lang.NullPointerException();
        }
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              case 10: {
                key_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000001;
                break;
              } // case 10
              case 16: {
                timestamp_ = input.readInt64();
                bitField0_ |= 0x00000002;
                break;
              } // case 16
              case 26: {
                com.google.protobuf.MapEntry<java.lang.String, java.lang.String>
                data__ = input.readMessage(
                    DataDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
                internalGetMutableData().getMutableMap().put(
                    data__.getKey(), data__.getValue());
                bitField0_ |= 0x00000004;
                break;
              } // case 26
              default: {
                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                  done = true; // was an endgroup tag
                }
                break;
              } // default:
            } // switch (tag)
          } // while (!done)
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.unwrapIOException();
        } finally {
          onChanged();
        } // finally
        return this;
      }
      private int bitField0_;

      private java.lang.Object key_ = "";
      /**
       * <code>string key = 1;</code>
       * @return The key.
       */
      public java.lang.String getKey() {
        java.lang.Object ref = key_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          key_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string key = 1;</code>
       * @return The bytes for key.
       */
      public com.google.protobuf.ByteString
          getKeyBytes() {
        java.lang.Object ref = key_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          key_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string key = 1;</code>
       * @param value The key to set.
       * @return This builder for chaining.
       */
      public Builder setKey(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        key_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <code>string key = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearKey() {
        key_ = getDefaultInstance().getKey();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
        return this;
      }
      /**
       * <code>string key = 1;</code>
       * @param value The bytes for key to set.
       * @return This builder for chaining.
       */
      public Builder setKeyBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        key_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }

      private long timestamp_ ;
      /**
       * <code>int64 timestamp = 2;</code>
       * @return The timestamp.
       */
      @java.lang.Override
      public long getTimestamp() {
        return timestamp_;
      }
      /**
       * <code>int64 timestamp = 2;</code>
       * @param value The timestamp to set.
       * @return This builder for chaining.
       */
      public Builder setTimestamp(long value) {

        timestamp_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <code>int64 timestamp = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearTimestamp() {
        bitField0_ = (bitField0_ & ~0x00000002);
        timestamp_ = 0L;
        onChanged();
        return this;
      }

      private com.google.protobuf.MapField<
          java.lang.String, java.lang.String> data_;
      private com.google.protobuf.MapField<java.lang.String, java.lang.String>
          internalGetData() {
        if (data_ == null) {
          return com.google.protobuf.MapField.emptyMapField(
              DataDefaultEntryHolder.defaultEntry);
        }
        return data_;
      }
      private com.google.protobuf.MapField<java.lang.String, java.lang.String>
          internalGetMutableData() {
        if (data_ == null) {
          data_ = com.google.protobuf.MapField.newMapField(
              DataDefaultEntryHolder.defaultEntry);
        }
        if (!data_.isMutable()) {
          data_ = data_.copy();
        }
        bitField0_ |= 0x00000004;
        onChanged();
        return data_;
      }
      public int getDataCount() {
        return internalGetData().getMap().size();
      }
      /**
       * <code>map&lt;string, string&gt; data = 3;</code>
       */
      @java.lang.Override
      public boolean containsData(
          java.lang.String key) {
        if (key == null) { throw new NullPointerException("map key"); }
        return internalGetData().getMap().containsKey(key);
      }
      /**
       * Use {@link #getDataMap()} instead.
       */
      @java.lang.Override
      @java.lang.Deprecated
      public java.util.Map<java.lang.String, java.lang.String> getData() {
        return getDataMap();
      }
      /**
       * <code>map&lt;string, string&gt; data = 3;</code>
       */
      @java.lang.Override
      public java.util.Map<java.lang.String, java.lang.String> getDataMap() {
        return internalGetData().getMap();
      }
      /**
       * <code>map&lt;string, string&gt; data = 3;</code>
       */
      @java.lang.Override
      public /* nullable */
java.lang.String getDataOrDefault(
          java.lang.String key,
          /* nullable */
java.lang.String defaultValue) {
        if (key == null) { throw new NullPointerException("map key"); }
        java.util.Map<java.lang.String, java.lang.String> map =
            internalGetData().getMap();
        return map.containsKey(key) ? map.get(key) : defaultValue;
      }
      /**
       * <code>map&lt;string, string&gt; data = 3;</code>
       */
      @java.lang.Override
      public java.lang.String getDataOrThrow(
          java.lang.String key) {
        if (key == null) { throw new NullPointerException("map key"); }
        java.util.Map<java.lang.String, java.lang.String> map =
            internalGetData().getMap();
        if (!map.containsKey(key)) {
          throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
      }
      public Builder clearData() {
        bitField0_ = (bitField0_ & ~0x00000004);
        internalGetMutableData().getMutableMap()
            .clear();
        return this;
      }
      /**
       * <code>map&lt;string, string&gt; data = 3;</code>
       */
      public Builder removeData(
          java.lang.String key) {
        if (key == null) { throw new NullPointerException("map key"); }
        internalGetMutableData().getMutableMap()
            .remove(key);
        return this;
      }
      /**
       * Use alternate mutation accessors instead.
       */
      @java.lang.Deprecated
      public java.util.Map<java.lang.String, java.lang.String>
          getMutableData() {
        bitField0_ |= 0x00000004;
        return internalGetMutableData().getMutableMap();
      }
      /**
       * <code>map&lt;string, string&gt; data = 3;</code>
       */
      public Builder putData(
          java.lang.String key,
          java.lang.String value) {
        if (key == null) { throw new NullPointerException("map key"); }
        if (value == null) { throw new NullPointerException("map value"); }
        internalGetMutableData().getMutableMap()
            .put(key, value);
        bitField0_ |= 0x00000004;
        return this;
      }
      /**
       * <code>map&lt;string, string&gt; data = 3;</code>
       */
      public Builder putAllData(
          java.util.Map<java.lang.String, java.lang.String> values) {
        internalGetMutableData().getMutableMap()
            .putAll(values);
        bitField0_ |= 0x00000004;
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:com.chuzi.imsdk.server.model.proto.SentBody)
    }

    // @@protoc_insertion_point(class_scope:com.chuzi.imsdk.server.model.proto.SentBody)
    private static final com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody();
    }

    public static com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<SentBody>
        PARSER = new com.google.protobuf.AbstractParser<SentBody>() {
      @java.lang.Override
      public SentBody parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        Builder builder = newBuilder();
        try {
          builder.mergeFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.setUnfinishedMessage(builder.buildPartial());
        } catch (com.google.protobuf.UninitializedMessageException e) {
          throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
        } catch (java.io.IOException e) {
          throw new com.google.protobuf.InvalidProtocolBufferException(e)
              .setUnfinishedMessage(builder.buildPartial());
        }
        return builder.buildPartial();
      }
    };

    public static com.google.protobuf.Parser<SentBody> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<SentBody> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.chuzi.imsdk.server.model.proto.SentBodyProto.SentBody getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_chuzi_imsdk_server_model_proto_SentBody_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_chuzi_imsdk_server_model_proto_SentBody_fieldAccessorTable;
  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_chuzi_imsdk_server_model_proto_SentBody_DataEntry_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_chuzi_imsdk_server_model_proto_SentBody_DataEntry_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016SentBody.proto\022\"com.chuzi.imsdk.server" +
      ".model.proto\"\235\001\n\010SentBody\022\013\n\003key\030\001 \001(\t\022\021" +
      "\n\ttimestamp\030\002 \001(\003\022D\n\004data\030\003 \003(\01326.com.ch" +
      "uzi.imsdk.server.model.proto.SentBody.Da" +
      "taEntry\032+\n\tDataEntry\022\013\n\003key\030\001 \001(\t\022\r\n\005val" +
      "ue\030\002 \001(\t:\0028\001B\017B\rSentBodyProtob\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_com_chuzi_imsdk_server_model_proto_SentBody_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_chuzi_imsdk_server_model_proto_SentBody_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_chuzi_imsdk_server_model_proto_SentBody_descriptor,
        new java.lang.String[] { "Key", "Timestamp", "Data", });
    internal_static_com_chuzi_imsdk_server_model_proto_SentBody_DataEntry_descriptor =
      internal_static_com_chuzi_imsdk_server_model_proto_SentBody_descriptor.getNestedTypes().get(0);
    internal_static_com_chuzi_imsdk_server_model_proto_SentBody_DataEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_chuzi_imsdk_server_model_proto_SentBody_DataEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
